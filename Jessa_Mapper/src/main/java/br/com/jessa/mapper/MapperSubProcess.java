package br.com.jessa.mapper;

import java.lang.reflect.Method;
import java.util.Map;

import br.com.jessa.mapper.annotations.CaseMap;
import br.com.jessa.mapper.annotations.Mapper;
import br.com.jessa.mapper.convert.ConvertMapperValues;
import br.com.jessa.mapper.exception.JessaMapperException;
import br.com.jessa.mapper.process.ProcessMapObjectClass;
import br.com.jessa.mapper.process.obj.ObjectClassMap;
import br.com.jessa.mapper.process.obj.ObjectProcessInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInvoke;

public class MapperSubProcess {

	public static Object processObject(Object source, Class destinyClass, Method mapperMethod) {
		Object destiny = ReflectionObjectInstance.byClasss(destinyClass);

		ObjectProcessInstance instance = new ObjectProcessInstance();

		instance.setObjectSource(source);
		instance.setObjectDestiny(destiny);
		instance.setSourceMap(ProcessMapObjectClass.generateMapObjectClass(instance.getObjectSource().getClass(),true));
		instance.populateCoumnName();

		Mapper mapperInterface = mapperMethod.getAnnotation(Mapper.class);
		if (mapperInterface != null) {
			for (CaseMap attr : mapperInterface.mapping()) {
				if (instance.getSourceMap().containsKey(attr.source()))
					MapperValidation.failIfNull(instance.getSourceMap().get(attr.source()))
							.setColumnName(attr.destiny());
			}
		}



		 Map<String, ObjectClassMap> destinyMap = ProcessMapObjectClass.generateMapObjectClass(instance.getObjectDestiny(),false);
	        for(Map.Entry<String, ObjectClassMap> sourceObject:instance.getSourceMap().entrySet()){
	        	ObjectClassMap sourceObjectClass = sourceObject.getValue();
	        	
	        	 if(!destinyMap.containsKey(sourceObjectClass.getColumnName()))continue;
	        	 Object valueGetBySource = new ReflectionObjectInvoke(sourceObjectClass.getMethodGet()).invoke(sourceObjectClass.getReference());
	             ObjectClassMap destinMapObject = destinyMap.get(sourceObjectClass.getColumnName());
	             Method methodSetDestiny = MapperValidation.failIfNull(destinMapObject).getMethodSet();
	             valueGetBySource = ConvertMapperValues.tryToConvert(valueGetBySource,methodSetDestiny.getParameterTypes(),mapperMethod);
	             new ReflectionObjectInvoke(methodSetDestiny).invoke(destinMapObject.getReference(),valueGetBySource);
	             
	        }
		 
	       return instance.getObjectDestiny();
	}

}
