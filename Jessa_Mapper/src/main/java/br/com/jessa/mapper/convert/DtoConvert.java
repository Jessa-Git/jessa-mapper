package br.com.jessa.mapper.convert;

import java.lang.reflect.Method;

public class DtoConvert {

	
	Object sourceObject;
	Class<?> returnValueClassDestiny;
	Method mapperMethod;
	Method methodSetDestiny;
	Method methodGetSource;
	public Object getSourceObject() {
		return sourceObject;
	}
	public void setSourceObject(Object sourceObject) {
		this.sourceObject = sourceObject;
	}
	public Class getReturnValueClassDestiny() {
		return returnValueClassDestiny;
	}
	public void setReturnValueClassDestiny(Class<?> returnValueClassDestiny) {
		this.returnValueClassDestiny = returnValueClassDestiny;
	}
	public Method getMapperMethod() {
		return mapperMethod;
	}
	public void setMapperMethod(Method mapperMethod) {
		this.mapperMethod = mapperMethod;
	}
	public Method getMethodSetDestiny() {
		return methodSetDestiny;
	}
	public void setMethodSetDestiny(Method methodSetDestiny) {
		this.methodSetDestiny = methodSetDestiny;
	}
	public Method getMethodGetSource() {
		return methodGetSource;
	}
	public void setMethodGetSource(Method methodGetSource) {
		this.methodGetSource = methodGetSource;
	}

	
	public String getClassSource() {
		return sourceObject.getClass().getSimpleName();
	}
	
}
