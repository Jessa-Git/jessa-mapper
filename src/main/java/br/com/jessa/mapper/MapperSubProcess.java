package br.com.jessa.mapper;

import java.lang.reflect.Method;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;
import br.com.jessa.mapper.reflection.ReflectionObjectInstance;

public class MapperSubProcess {

	MapperSubProcess() {
		throw new JessaMapperException(ExceptionsMessages.PRIVATE_CONSTRUCTOR.getMessage());
	}

	public static Object processObject(Object source, Class<?> destinyClass, Method method) {
		
		Object destiny = ReflectionObjectInstance.byClasss(destinyClass);

		return MapperAction.process(source, destiny, method);
	}
}
