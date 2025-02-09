package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertModel<T> {
	
	private T value;
	protected static <T> T doThis(HowToConvert h) {
		T b = null;
		try {

			b = h.newValue(h.getValue());

		} catch (Exception e) {
			JessaMapperException.out(e, "Falha ao tentar converter valor");
		}
		return b;
	}
	
	protected void setValue(T newValue) {
		value = newValue;
	}
	
	public T getValue() {		
		return value;
	}

}
