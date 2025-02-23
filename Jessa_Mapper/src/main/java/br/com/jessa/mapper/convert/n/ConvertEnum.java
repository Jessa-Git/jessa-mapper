package br.com.jessa.mapper.convert.n;

import java.lang.reflect.Method;

import br.com.jessa.mapper.convert.DtoConvert;
import br.com.jessa.mapper.reflection.ReflectionObjectInvoke;

public class ConvertEnum {
	private DtoConvert dto;
	private Object val;

	private Object processsEnumMethods(Object enumSingleObject) {
		Class<?> sourceClass = dto.getSourceObject().getClass();
		Method[] enumMethods = dto.getReturnValueClassDestiny().getDeclaredMethods();
		for (Method enumMethod : enumMethods) {
			if (!sourceClass.equals(enumMethod.getReturnType()))
				continue;
			Object enumValue = new ReflectionObjectInvoke(enumMethod).invoke(enumSingleObject);
			if (!dto.getSourceObject().equals(enumValue))
				continue;
			return enumSingleObject;

		}
		return null;
	}

	private Object[] getEnumObjects() {
		Object[] enumObjects = null;
		Method values;
		try {
			values = dto.getReturnValueClassDestiny().getMethod("values");
			enumObjects = (Object[]) new ReflectionObjectInvoke(values).invoke(null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return enumObjects;

	}

	public Object getVal() {
		return val;
	}

	public ConvertEnum process(DtoConvert dto) {
		this.dto = dto;

		Object toReturn = null;

		for (Object enumSingleObject : getEnumObjects()) {

			if (enumSingleObject.toString().equals(dto.getSourceObject().toString())) {
				toReturn = enumSingleObject;
				break;
			}

			toReturn = processsEnumMethods(enumSingleObject);
			if (toReturn != null)
				break;
		}
		if (toReturn != null)
			val = toReturn;

		return this;
	}
}
