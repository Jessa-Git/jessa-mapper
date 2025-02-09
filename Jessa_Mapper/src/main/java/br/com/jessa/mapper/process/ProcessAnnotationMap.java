package br.com.jessa.mapper.process;

import java.lang.reflect.Method;

import br.com.jessa.mapper.MapperValidation;
import br.com.jessa.mapper.annotations.CaseMap;
import br.com.jessa.mapper.annotations.Mapper;
import br.com.jessa.mapper.process.obj.ObjectProcessInstance;

public class ProcessAnnotationMap {

	public void s(Method method,ObjectProcessInstance instance) {
		Mapper mapperInterface = method.getAnnotation(Mapper.class);
		if (mapperInterface == null)
			return;
		for (CaseMap attr : mapperInterface.mapping()) {
			if (instance.getSourceMap().containsKey(attr.source()))
				MapperValidation.failIfNull(instance.getSourceMap().get(attr.source())).setColumnName(attr.destiny());

		}
	}
}
