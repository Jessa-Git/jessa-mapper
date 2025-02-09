package br.com.jessa.mapper.process;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import br.com.jessa.mapper.exception.JessaMapperException;
import br.com.jessa.mapper.process.obj.DetectClass;
import br.com.jessa.mapper.process.obj.ObjectClassMap;
import br.com.jessa.mapper.reflection.ReflectionObjectInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInvoke;

public class ProcessMapObjectClass {
	
	public static Map<String ,ObjectClassMap > generateMapObjectClass(Object m,Boolean isSource){
		return generateMapObjectClass(m,null,isSource);
	}
	
	private static Map<String ,ObjectClassMap > generateMapObjectClass(Object j,String superName,Boolean isSource){
		if(j == null)return null;
		Class<?> m = j.getClass();
		Method[] methods = m.getDeclaredMethods();
        Field[] fields = m.getDeclaredFields();
        Map<String ,ObjectClassMap > mapObjectInClass = new HashMap<>();
        processAllFieldInClass(methods, fields,superName, mapObjectInClass,j,isSource);
        return mapObjectInClass;
    }
	
	private static void processAllFieldInClass(Method[] methods, Field[] fields,String superName, Map<String, ObjectClassMap> mapObjectInClass,Object reference,Boolean isSource) {
        for (Field field : fields) {
			int u = 0;
        	ObjectClassMap cls = new ObjectClassMap();
            cls.setField(field);
            cls.setReference(reference);
            
            processMethodGetSetToField(methods, field.getName(), cls);
            
            mapObjectInClass.put(naming(superName,cls.getFieldName()),cls);
            if(!DetectClass.isPrimitive(field.getType())) {
	            JessaMapperException.protectStackOverFlowByClass(reference.getClass(),field);
	            Object suRef = null;
	            if(isSource) {
	            	suRef=getNewObject(cls, reference);
	            	
	            }else {
	            	suRef=ReflectionObjectInstance.byClasss(field.getType());
	            	setNewObject(cls, reference, suRef);
	            	
	            }
	            Map<String ,ObjectClassMap > o=generateMapObjectClass(suRef,naming(superName,cls.getFieldName()),isSource);
	            if(o!=null)mapObjectInClass.putAll(o);
	            
	            
            }
        }
    }
	private static Object getNewObject(ObjectClassMap cls,Object reference) {
		return new ReflectionObjectInvoke(cls.getMethodGet()).invoke(reference);
	}
	private static void setNewObject(ObjectClassMap cls,Object reference, Object suRef ) {
		new ReflectionObjectInvoke(cls.getMethodSet()).invoke(reference, suRef);
	}
	private static String naming(String a,String b) {
		if(a !=null)
			return a+"."+b;
		return b;
	}
	 private static void processMethodGetSetToField(Method[] methods, String name, ObjectClassMap cls) {
	        for(Method ms: methods){
	            if(!ms.getName().toLowerCase().contains(name.toLowerCase()))continue;
	            if(ms.getName().contains("get")) cls.setMethodGet(ms);
	            if(ms.getName().contains("set")) cls.setMethodSet(ms);
	        }
	    } 
}
