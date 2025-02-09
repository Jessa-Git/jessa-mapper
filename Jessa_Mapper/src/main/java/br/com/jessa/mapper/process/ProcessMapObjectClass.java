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

public class ProcessMapObjectClass {
	
	public static Map<String ,ObjectClassMap > generateMapObjectClass(Object m){
		System.out.println(">>"+m.getClass().getSimpleName());
			return generateMapObjectClass(m,null);
	}
	
	private static Map<String ,ObjectClassMap > generateMapObjectClass(Object j,String superName){

		Class<?> m = j.getClass();
		System.out.println("-ProcessMapObjectClass ["+m.getSimpleName()+"]"+m.isPrimitive());
        Method[] methods = m.getDeclaredMethods();
        Field[] fields = m.getDeclaredFields();
        Map<String ,ObjectClassMap > mapObjectInClass = new HashMap<>();
        processAllFieldInClass(methods, fields,superName, mapObjectInClass,j);
        return mapObjectInClass;
    }
	
	private static void processAllFieldInClass(Method[] methods, Field[] fields,String superName, Map<String, ObjectClassMap> mapObjectInClass,Object reference) {
        System.out.println("REF >>"+reference);
		for (Field field : fields) {
			int u = 0;
        	ObjectClassMap cls = new ObjectClassMap();
            cls.setField(field);
            cls.setReference(reference);
//            System.out.println(">>ProcessMapObjectClass ["+field.getName()+"]"
//            		+field.getType().getSimpleName()
//            		+".."+DetectClass.isPrimitive(field.getType()));
            
            processMethodGetSetToField(methods, field.getName(), cls);
            
            mapObjectInClass.put(naming(superName,cls.getFieldName()),cls);
            if(!DetectClass.isPrimitive(field.getType())) {
	            System.out.println("*** Sub process to["+u+"]:"+naming(superName,cls.getFieldName()));
	            JessaMapperException.protectStackOverFlowByClass(reference.getClass(),field);
	            Object suRef=ReflectionObjectInstance.byClasss(field.getType());
	            System.out.println("REF:"+suRef.toString());
	            Map<String ,ObjectClassMap > o=generateMapObjectClass(suRef,naming(superName,cls.getFieldName()));
	            setNewObject(cls, reference, suRef);
	            if(o!=null)mapObjectInClass.putAll(o);
            }else {
            	System.out.println("*** process to["+u+"]:"+naming(superName,cls.getFieldName()));
            }
        }
    }
	
	private static void setNewObject(ObjectClassMap cls,Object reference, Object suRef ) {
		try {
			System.out.println("Mak vinc>>>"+cls.getFieldName()+"."+cls.getMethodSet().getName());
			cls.getMethodSet().invoke(reference, suRef);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
