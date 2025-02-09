package br.com.jessa.mapper.process;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;
import br.com.jessa.mapper.process.obj.DetectClass;
import br.com.jessa.mapper.process.obj.ObjectClassMap;
import br.com.jessa.mapper.reflection.ReflectionObjectInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInvoke;

public class ProcessMapObjectClass {

	ProcessMapObjectClass() {
		throw new JessaMapperException(ExceptionsMessages.PRIVATE_CONSTRUCTOR.getMessage());
	}

	public static Map<String, ObjectClassMap> generateMapObjectClass(Object m, Boolean isSource) {
		return generateMapObjectClass(m, null, isSource);
	}

	private static Map<String, ObjectClassMap> generateMapObjectClass(Object j, String superName, Boolean isSource) {
		if (j == null)
			return Collections.emptyMap();
		Class<?> m = j.getClass();
		Method[] methods = m.getDeclaredMethods();
		Field[] fields = m.getDeclaredFields();

		return processAllFieldInClass(methods, fields, superName, j, isSource);
	}

	private static Map<String, ObjectClassMap> processAllFieldInClass(Method[] methods, Field[] fields,
			String superName, Object reference, Boolean isSource) {
		Map<String, ObjectClassMap> mapObjectInClass = new HashMap<>();
		for (Field field : fields) {
			ObjectClassMap cls = new ObjectClassMap();
			cls.setField(field);
			cls.setReference(reference);

			processMethodGetSetToField(methods, field.getName(), cls);

			mapObjectInClass.put(naming(superName, cls.getFieldName()), cls);
			if (Boolean.FALSE.equals(DetectClass.isPrimitive(field.getType()))) {
				JessaMapperException.protectStackOverFlowByClass(reference.getClass(), field);
				Object suRef = generateNewInstanceObject(reference, isSource, field, cls);
				Map<String, ObjectClassMap> o = generateMapObjectClass(suRef, naming(superName, cls.getFieldName()),isSource);
				if (o != null)
					mapObjectInClass.putAll(o);

			}
		}
		return mapObjectInClass;
	}

	private static Object generateNewInstanceObject(Object reference, Boolean isSource, Field field,
			ObjectClassMap cls) {
		if (Boolean.TRUE.equals(isSource))
			return getNewObject(cls, reference);
		return setNewObject(cls, reference, ReflectionObjectInstance.byClasss(field.getType()));

	}

	private static Object getNewObject(ObjectClassMap cls, Object reference) {
		return new ReflectionObjectInvoke(cls.getMethodGet()).invoke(reference);
	}

	private static Object setNewObject(ObjectClassMap cls, Object reference, Object suRef) {
		new ReflectionObjectInvoke(cls.getMethodSet()).invoke(reference, suRef);
		return suRef;
	}

	private static String naming(String a, String b) {
		if (a != null)
			return a + "." + b;
		return b;
	}

	private static void processMethodGetSetToField(Method[] methods, String name, ObjectClassMap cls) {
		for (Method ms : methods) {
			if (!ms.getName().toLowerCase().contains(name.toLowerCase())) continue;
			if (ms.getName().contains("get")) cls.setMethodGet(ms);
			if (ms.getName().contains("set")) cls.setMethodSet(ms);
		}
	}
}
