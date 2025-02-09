package br.com.jessa.mapper.convert;

import java.lang.reflect.Method;

import br.com.jessa.mapper.MapperSubProcess;

public class ConvertToClass<T> extends ConvertModel<T> {

	public static HowToConvert reMap(Object sourceElement, Class aClass,Method mapperMethod) {
		return new HowToConvert(sourceElement) {
			
			@SuppressWarnings("unchecked")
			@Override
			public  Object newValue(Object valueToConvert) {
				return MapperSubProcess.processObject(valueToConvert, aClass, mapperMethod);
			}
		};
	}

}
