package br.com.jessa.mapper.process;

import java.lang.reflect.Method;
import java.util.Map;

import br.com.jessa.mapper.MapperValidation;
import br.com.jessa.mapper.convert.DtoConvert;
import br.com.jessa.mapper.convert.n.JessaConvert;
import br.com.jessa.mapper.process.obj.ObjectClassMap;
import br.com.jessa.mapper.process.obj.ObjectProcessInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInvoke;

public class MapGettingSourceToSetDestiny {

	public void process(ObjectProcessInstance instance, Method method) {
		Map<String, ObjectClassMap> destinyMap = ProcessMapObjectClass
				.generateMapObjectClass(instance.getObjectDestiny(), false);

		for (Map.Entry<String, ObjectClassMap> sourceObject : instance.getSourceMap().entrySet()) {
			ObjectClassMap sourceMapClass = sourceObject.getValue();

			if (!destinyMap.containsKey(sourceMapClass.getColumnName()))
				continue;

			ObjectClassMap destinyMapClass = destinyMap.get(sourceMapClass.getColumnName());

			Method methodSetDestiny = MapperValidation.failIfNull(destinyMapClass).getMethodSet();

			setDestinyValueWithSourceValue(method, sourceMapClass, destinyMapClass.getReference(), methodSetDestiny);

		}
	}

	private void setDestinyValueWithSourceValue(Method method, ObjectClassMap sourceMapClass, Object destinyMapClass,
			Method methodSetDestiny) {
		new ReflectionObjectInvoke(methodSetDestiny).invoke(destinyMapClass,
				getValueFromSourceAndConvert(method, sourceMapClass, methodSetDestiny));
	}

	private Object getValueFromSourceAndConvert(Method method, ObjectClassMap sourceMapClass, Method methodSetDestiny) {
		Object valueGetBySource = new ReflectionObjectInvoke(sourceMapClass.getMethodGet())
				.invoke(sourceMapClass.getReference());

		if (valueGetBySource != null) {
			JessaConvert convert = new JessaConvert(prepareDto(method, sourceMapClass, methodSetDestiny, valueGetBySource));
			convert
			.byPrimitiveValue()
			.byClassObject()
			.byEnumObject();

			return convert.val();
		}
		return null;
	}

	private DtoConvert prepareDto(Method method, ObjectClassMap sourceMapClass, Method methodSetDestiny,
			Object valueGetBySource) {
		DtoConvert dto = new DtoConvert();
		dto.setMethodGetSource(sourceMapClass.getMethodGet());
		dto.setMethodSetDestiny(methodSetDestiny);
		dto.setMapperMethod(method);
		dto.setReturnValueClassDestiny(methodSetDestiny.getParameterTypes()[0]);
		dto.setSourceObject(valueGetBySource);
		return dto;
	}

}
