package br.com.jessa.mapper;

import java.lang.reflect.Method;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;
import br.com.jessa.mapper.factory.FactoryObjectInstance;
import br.com.jessa.mapper.process.MapAnnotation;
import br.com.jessa.mapper.process.MapGettingSourceToSetDestiny;
import br.com.jessa.mapper.process.obj.ObjectProcessInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInstance;

public class MapperSubProcess {

	MapperSubProcess() {
		throw new JessaMapperException(ExceptionsMessages.PRIVATE_CONSTRUCTOR.getMessage());
	}
	public static Object processObject(Object source, Class<?> destinyClass, Method mapperMethod) {
		Object destiny = ReflectionObjectInstance.byClasss(destinyClass);


		 ObjectProcessInstance instance = new FactoryObjectInstance().create(source, destiny);
			
		 new MapAnnotation().process( instance,mapperMethod);
		 new MapGettingSourceToSetDestiny().process(instance, mapperMethod);
		 
	       return instance.getObjectDestiny();
	}

}
