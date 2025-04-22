package br.com.jessa.mapper.factory;

import br.com.jessa.mapper.process.ProcessMapObjectClass;
import br.com.jessa.mapper.process.obj.ObjectProcessInstance;

public class FactoryObjectInstance {
	
	public static FactoryObjectInstance instance() {
		return new FactoryObjectInstance();
	}
	public ObjectProcessInstance create(Object sourcer, Object destiny) {
		ObjectProcessInstance instance = new ObjectProcessInstance();

		instance.setObjectDestiny(destiny);
		instance.setSourceMap(ProcessMapObjectClass.generateMapObjectClass(sourcer,true));
		instance.populateCoumnName();
		return instance;
	}

}
