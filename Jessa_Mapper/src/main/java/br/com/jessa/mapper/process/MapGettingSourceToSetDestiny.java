package br.com.jessa.mapper.process;

import java.lang.reflect.Method;
import java.util.Map;

import br.com.jessa.mapper.MapperValidation;
import br.com.jessa.mapper.convert.ConvertMapperValues;
import br.com.jessa.mapper.process.obj.ObjectClassMap;
import br.com.jessa.mapper.process.obj.ObjectProcessInstance;
import br.com.jessa.mapper.reflection.ReflectionObjectInvoke;

public class MapGettingSourceToSetDestiny {

	public void process(ObjectProcessInstance instance, Method method) {
		Map<String, ObjectClassMap> destinyMap = ProcessMapObjectClass
				.generateMapObjectClass(instance.getObjectDestiny(), false);

		for (Map.Entry<String, ObjectClassMap> sourceObject : instance.getSourceMap().entrySet()) {
			ObjectClassMap sourceMapClass = sourceObject.getValue();

			if (!destinyMap.containsKey(sourceMapClass.getColumnName()))
				continue;

			ObjectClassMap destinyMapClass = destinyMap.get(sourceMapClass.getColumnName());

			Method methodSetDestiny = MapperValidation.failIfNull(destinyMapClass).getMethodSet();

			setDestinyValueWithSourceValue(method, sourceMapClass, destinyMapClass.getReference(), methodSetDestiny);

		}
	}

	private void setDestinyValueWithSourceValue(Method method, ObjectClassMap sourceMapClass, Object destinyMapClass,
			Method methodSetDestiny) {
		new ReflectionObjectInvoke(methodSetDestiny).invoke(destinyMapClass,
				getValueFromSourceAndConvert(method, sourceMapClass, methodSetDestiny));
	}

	private Object getValueFromSourceAndConvert(Method method, ObjectClassMap sourceMapClass, Method methodSetDestiny) {
		Object valueGetBySource = new ReflectionObjectInvoke(sourceMapClass.getMethodGet())
				.invoke(sourceMapClass.getReference());
		return ConvertMapperValues.tryToConvert(valueGetBySource, methodSetDestiny, method,
				sourceMapClass.getMethodGet());
	}

}
