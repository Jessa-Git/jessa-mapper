package br.com.jessa.mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

import br.com.jessa.mapper.annotations.CaseMap;
import br.com.jessa.mapper.annotations.Mapper;
import br.com.jessa.mapper.convert.ConvertMapperValues;
import br.com.jessa.mapper.exception.JessaMapperException;
import br.com.jessa.mapper.process.ObjectClassMap;
import br.com.jessa.mapper.process.ObjectProcessInstance;
import br.com.jessa.mapper.process.ProcessMapObjectClass;
import br.com.jessa.mapper.reflection.ReflectionObjectInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInvoke;

public class ProcessMapper implements InvocationHandler{
	
	 @Override
	    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		 
		 
		 ObjectProcessInstance instance = new ObjectProcessInstance();
		 
		 instance.setObjectSource(getObjectSource(args));
		 instance.setObjectDestiny(getObjectDestiny(method, args));
		 instance.setSourceMap(ProcessMapObjectClass.generateMapObjectClass(instance.getObjectSource().getClass()));
		 instance.populateCoumnName();
		 
		 
		 
		Mapper mapperInterface = method.getAnnotation(Mapper.class);
        if(mapperInterface != null) {
	        for(CaseMap attr : mapperInterface.mapping()){
	            if(instance.getSourceMap().containsKey(attr.source()))
	                MapperValidation.failIfNull(instance.getSourceMap().get(attr.source())).setColumnName(attr.destiny());
	        }
        }
        
        
        Map<String, ObjectClassMap> destinyMap = ProcessMapObjectClass.generateMapObjectClass(instance.getObjectDestiny().getClass());
        for(Map.Entry<String, ObjectClassMap> sourceObject:instance.getSourceMap().entrySet()){
        	ObjectClassMap sourceObjectClass = sourceObject.getValue();
        	 if(!destinyMap.containsKey(sourceObjectClass.getColumnName()))continue;
             Object valueGetBySource = new ReflectionObjectInvoke(sourceObjectClass.getMethodGet()).invoke(instance.getObjectSource());
             Method methodSetDestiny = MapperValidation.failIfNull(destinyMap.get(sourceObjectClass.getColumnName())).getMethodSet();
             valueGetBySource = ConvertMapperValues.tryToConvert(valueGetBySource,methodSetDestiny.getParameterTypes());
             new ReflectionObjectInvoke(methodSetDestiny).invoke(instance.getObjectDestiny(),valueGetBySource);
        }
		 
		 
	       return instance.getObjectDestiny();
	    }

	 private Object getObjectSource(Object[] args){
	        JessaMapperException.checkSourceObject(args);
	        return args[0];
	    }
	    private Object getObjectDestiny(Method method, Object[] args){
	    	String methodClassReturn = method.getReturnType().getSimpleName();
	        if ("void".equals(methodClassReturn)) {
	            return args[1];
	        }
	        return ReflectionObjectInstance.byMethod(method);
	    }

}
