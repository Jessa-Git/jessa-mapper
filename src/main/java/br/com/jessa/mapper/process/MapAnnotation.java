package br.com.jessa.mapper.process;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import br.com.jessa.mapper.MapperValidation;
import br.com.jessa.mapper.annotations.CaseEnumMap;
import br.com.jessa.mapper.annotations.CaseMap;
import br.com.jessa.mapper.annotations.Mapper;
import br.com.jessa.mapper.exception.JessaMapperException;
import br.com.jessa.mapper.process.obj.ObjectProcessInstance;

public class MapAnnotation {

	public void process(ObjectProcessInstance instance, Method method) {
		Mapper mapperInterface = method.getAnnotation(Mapper.class);
		if (mapperInterface == null)
			return;

		checkDestinyMappin(instance, mapperInterface.mapping());
		for (CaseMap attr : mapperInterface.mapping()) {
			if (instance.getSourceMap().containsKey(attr.source()))
				MapperValidation.failIfNull(instance.getSourceMap().get(attr.source())).setColumnName(attr.destiny());
		}
	}

	private void checkDestinyMappin(ObjectProcessInstance instance, CaseMap[] mapping) {
		for (CaseMap attr : mapping) {
			boolean checkField = false;
			String fieldName = attr.destiny();
			for (Field field : instance.getObjectDestiny().getClass().getDeclaredFields()) {
				if (fieldName.equals(field.getName())) {
					checkField = true;
					break;
				}
			}
			if(!checkField) {
				throw new JessaMapperException("Erro no mapping: nome ["+fieldName+"] não encontrado na classe "+instance.getObjectDestiny().getClass().getSimpleName());
			}
		}
	}

	public void processEnum(ObjectProcessInstance instance, Method method) {
		Mapper mapperInterface = method.getAnnotation(Mapper.class);
		if (mapperInterface == null)
			return;

		instance.getSourceMap().forEach((key, val) -> {
			for (CaseEnumMap attr : mapperInterface.mapEnum()) {
				String fieldName = attr.fieldNameClass();
				String methodName = attr.parameterNameEnum();
				if (methodName != null && val.getFieldName().equals(fieldName))
					val.setEnumMethod(methodName);
			}
		});

	}

}
