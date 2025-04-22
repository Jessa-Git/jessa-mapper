package br.com.jessa.mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import br.com.jessa.mapper.exception.JessaMapperException;
import br.com.jessa.mapper.reflection.ReflectionObjectInstance;

public class MapperHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		Object source = getObjectSource(args);
		Object destiny = getObjectDestiny(method, args);
		
		return MapperAction.process(source, destiny, method);
	}

	private Object getObjectSource(Object[] args) {
		JessaMapperException.checkSourceObject(args);
		return args[0];
	}

	private Object getObjectDestiny(Method method, Object[] args) {
		String methodClassReturn = method.getReturnType().getSimpleName();
		if ("void".equals(methodClassReturn)) {
			return args[1];
		}
		return ReflectionObjectInstance.byMethod(method);
	}

}
