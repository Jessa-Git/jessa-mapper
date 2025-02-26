package br.com.jessa.mapper.convert.n;

import java.lang.reflect.Method;

import br.com.jessa.mapper.convert.DtoConvert;
import br.com.jessa.mapper.reflection.ReflectionObjectInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInvoke;

public class ConvertEnum {
	private DtoConvert dto;
	private Object val;

	public ConvertEnum process(DtoConvert dto) {
		this.dto = dto;

		Object toReturn = null;

		for (Object enumSingleObject : getEnumObjects(dto.getReturnValueClassDestiny())) {

			if (enumSingleObject.toString().equals(dto.getSourceObject().toString())) {
				toReturn = enumSingleObject;
				break;
			}

			toReturn = processsEnumMethodsDestiny(enumSingleObject);
			if (toReturn != null)
				break;
		}
		if (toReturn != null)
			val = toReturn;

		return this;
	}

	public ConvertEnum processSource(DtoConvert dto) {
		if (dto.getEnumMethodName() == null)
			return this;

		Object enumSourceValue = dto.getSourceObject();
		if (!enumSourceValue.getClass().isEnum())
			return this;

		Class<?> sourceClass = enumSourceValue.getClass();
		val = new ReflectionObjectInvoke().invokeByMethod(sourceClass, dto.getEnumMethodName(), enumSourceValue);
		return this;
	}

	private Object processsEnumMethodsDestiny(Object enumSingleObject) {
		for (Method enumMethod : dto.getReturnValueClassDestiny().getDeclaredMethods()) {
			if (Boolean.TRUE.equals(compareEnumValueToReturnEnumObject(enumMethod, enumSingleObject)))
				continue;
			return enumSingleObject;
		}
		return null;
	}

	private Boolean compareEnumValueToReturnEnumObject(Method enumMethod, Object enumSingleObject) {
		Class<?> sourceClass = dto.getSourceObject().getClass();

		if (!sourceClass.equals(enumMethod.getReturnType()))
			return Boolean.TRUE;

		Object enumValue = new ReflectionObjectInvoke(enumMethod).invoke(enumSingleObject);
		if (!dto.getSourceObject().equals(enumValue))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	private Object[] getEnumObjects(Class<?> enumClass) {
		Method values = ReflectionObjectInstance.getMethod(enumClass, "values");
		return (Object[]) new ReflectionObjectInvoke(values).invoke(null);
	}

	public Object getVal() {
		return val;
	}

}
