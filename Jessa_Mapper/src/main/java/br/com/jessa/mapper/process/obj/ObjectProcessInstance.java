package br.com.jessa.mapper.process.obj;

import java.util.Map;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ObjectProcessInstance {
    private Object objectDestiny;

    private Map<String, ObjectClassMap> sourceMap;

	

	public Object getObjectDestiny() {
		return objectDestiny;
	}

	public void setObjectDestiny(Object objectDestiny) {
		this.objectDestiny = objectDestiny;
	}

	public Map<String, ObjectClassMap> getSourceMap() {
		return sourceMap;
	}

	public void setSourceMap(Map<String, ObjectClassMap> sourceMap) {
		this.sourceMap = sourceMap;
	}
    
    public void populateCoumnName() {
    	for(Map.Entry<String, ObjectClassMap> attr : getSourceMap().entrySet()){
	           attr.getValue().setColumnName(attr.getValue().getFieldName());
	        }
    }

}
