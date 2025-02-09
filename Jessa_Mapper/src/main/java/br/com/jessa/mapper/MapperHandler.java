package br.com.jessa.mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import br.com.jessa.mapper.exception.JessaMapperException;
import br.com.jessa.mapper.factory.FactoryObjectInstance;
import br.com.jessa.mapper.process.MapAnnotation;
import br.com.jessa.mapper.process.MapGettingSourceToSetDestiny;
import br.com.jessa.mapper.process.obj.ObjectProcessInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInstance;

public class MapperHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		ObjectProcessInstance instance = FactoryObjectInstance.instance()
				.create(getObjectSource(args),getObjectDestiny(method, args));

		new MapAnnotation().process(instance, method);

		new MapGettingSourceToSetDestiny().process(instance, method);

		return instance.getObjectDestiny();
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
