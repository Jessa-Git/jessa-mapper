package br.com.jessa.mapper;

import java.lang.reflect.Method;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;

public class MapperValidation {
	MapperValidation() {
		throw new JessaMapperException(ExceptionsMessages.PRIVATE_CONSTRUCTOR.getMessage());
	}

	public static <T extends Object> T failIfNull(T b) {
		JessaMapperException.isNull(b);
		return b;
	}

	public static void invokeSetWithParameter(Method method, Object ex) {
		Class<?> classObjectParameter = ex.getClass();
		JessaMapperException.isNull(method.getParameterTypes());
		JessaMapperException.methodWithMoreParameterThanOne("Metodo Set com mais de 1 parametro",method.getParameterTypes());
		Class<?> parameterTypes = method.getParameterTypes()[0];
		if (!parameterTypes.equals(classObjectParameter) && !parameterTypes.isPrimitive()
				)
			throw new JessaMapperException("Classe diferente entre Destino[" + method.getName() + "."
					+ parameterTypes.getName() + "] e Source[" + classObjectParameter.getName() + "]---");
	}
}
