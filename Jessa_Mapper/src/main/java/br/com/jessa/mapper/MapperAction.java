package br.com.jessa.mapper;

import java.lang.reflect.Method;

import br.com.jessa.mapper.factory.FactoryObjectInstance;
import br.com.jessa.mapper.process.MapAnnotation;
import br.com.jessa.mapper.process.MapGettingSourceToSetDestiny;
import br.com.jessa.mapper.process.obj.ObjectProcessInstance;

public class MapperAction {

	public static Object process(Object source ,Object destiny,Method method) {
		
		ObjectProcessInstance instance = FactoryObjectInstance.instance().create(source, destiny);

		new MapAnnotation().processEnum(instance, method);
		new MapAnnotation().process(instance, method);
		

		new MapGettingSourceToSetDestiny().process(instance, method);

		return instance.getObjectDestiny();
	}
}
