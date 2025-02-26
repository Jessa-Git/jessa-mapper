package br.com.jessa.mapper.reflection;

import java.lang.reflect.Method;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;

public class ReflectionObjectInstance {
	ReflectionObjectInstance() {
		throw new JessaMapperException(ExceptionsMessages.PRIVATE_CONSTRUCTOR.getMessage());
	}

	public static Method getMethod(Class<?> searchMethod, String name) {
		Method method = null;
		if (name == null) {
			return null;
		}
		try {
			method = searchMethod.getMethod(name);
		} catch (Exception e) {
			JessaMapperException.out(e, "Erro ao gerar instancia para metodo.");
		}

		return method;
	}

	public static Object byMethod(Method method) {
		Object objectToReturn = null;
		try {
			Class<?> returnObject = method.getReturnType();
			if (returnObject != null && !"void".equals(returnObject.getName())) {

				objectToReturn = returnObject.getConstructor().newInstance();
			}
		} catch (Exception e) {
			JessaMapperException.out(e, "Erro ao gerar instancia para metodo.");
		}

		return objectToReturn;
	}

	public static Object byClasss(Class<?> classToInstance) {
		try {
			return classToInstance.getConstructor().newInstance();
		} catch (Exception e) {
			JessaMapperException.out(e,
					"Erro ao gerar instancia para Classe[" + classToInstance.getSimpleName() + "].");
		}

		throw new JessaMapperException("Falha ao gerar Objeto por class:" + classToInstance.getSimpleName());
	}

}
