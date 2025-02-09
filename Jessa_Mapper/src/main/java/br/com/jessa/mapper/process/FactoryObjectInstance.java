package br.com.jessa.mapper.process;

import br.com.jessa.mapper.process.obj.ObjectProcessInstance;

public class FactoryObjectInstance {
	public ObjectProcessInstance create(Object sourcer, Object destiny) {
		ObjectProcessInstance instance = new ObjectProcessInstance();

		instance.setObjectSource(sourcer);
		instance.setObjectDestiny(destiny);
		instance.setSourceMap(ProcessMapObjectClass.generateMapObjectClass(instance.getObjectSource()));
		instance.populateCoumnName();

		return instance;
	}

}
