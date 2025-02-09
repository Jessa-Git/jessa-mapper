package br.com.jessa.mapper.process.obj;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObjectClassMap {

	private String columnName;
	private Method methodGet;
	private Method methodSet;
	private Field field;
	private Object reference;

	public Method getMethodGet() {
		return methodGet;
	}

	public void setMethodGet(Method methodGet) {
		this.methodGet = methodGet;
	}

	public Method getMethodSet() {
		return methodSet;
	}

	public void setMethodSet(Method methodSet) {
		this.methodSet = methodSet;
	}

	public Field getField() {
		return field;
	}

	public String getFieldName() {
		return field.getName();
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setField(Field field) {

		this.field = field;
	}

	@Override
	public String toString() {
		String ret;
		ret = "ClassObject{" + "methodGet=" + (methodGet == null ? "Metodo GET NULL" : methodGet.getName())
				+ ", methodSet=" + (methodSet == null ? "Metodo SET NULL" : methodSet.getName()) + ", field="
				+ (field == null ? "Field  NULL" : field.getName()) + '}';

		return ret;
	}

	public Object getReference() {
		return reference;
	}

	public void setReference(Object reference) {
		this.reference = reference;
	}

}
