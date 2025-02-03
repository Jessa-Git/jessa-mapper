package br.com.jessa.mapper.process;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ProcessMapObjectClass {

	public static Map<String ,ObjectClassMap > generateMapObjectClass(Class<?> m){
        Method[] methods = m.getDeclaredMethods();
        Field[] fields = m.getDeclaredFields();
        Map<String ,ObjectClassMap > mapObjectInClass = new HashMap<>();
        processAllFieldInClass(methods, fields, mapObjectInClass);
        return mapObjectInClass;
    }
	private static void processAllFieldInClass(Method[] methods, Field[] fields, Map<String, ObjectClassMap> mapObjectInClass) {
        for (Field field : fields) {
        	ObjectClassMap cls = new ObjectClassMap();
            cls.setField(field);
            processMethodGetSetToField(methods, field.getName(), cls);
            mapObjectInClass.put(cls.getFieldName(),cls);
        }
    }
	 private static void processMethodGetSetToField(Method[] methods, String name, ObjectClassMap cls) {
	        for(Method ms: methods){
	            if(!ms.getName().toLowerCase().contains(name.toLowerCase()))continue;
	            if(ms.getName().contains("get")) cls.setMethodGet(ms);
	            if(ms.getName().contains("set")) cls.setMethodSet(ms);
	        }
	    } 
}
