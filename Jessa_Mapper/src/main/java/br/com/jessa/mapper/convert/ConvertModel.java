package br.com.jessa.mapper.convert;

import java.lang.reflect.Method;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertModel<T> {
	
	private T value;
	protected Method methodSetDestiny;
	protected Method getSourceMethod;
	@SuppressWarnings("hiding")
	protected  <T> T doThis(HowToConvert h) {
		T b = null;
		try {

			b = h.newValue(h.getValue());

		} catch (Exception e) {			
			throw new JessaMapperException(ExceptionsMessages.CONVERT_VALUES.getMessage(methodSetDestiny.getName(),methodSetDestiny.getParameters()[0].getType().getSimpleName(),h.getValue()));
		}
		return b;
	}
	/*
	 * @SuppressWarnings({ "unchecked", "hiding" }) protected <T> T obj(HowToConvert
	 * h) { T b = null; try {
	 * 
	 * b = (T) h.getValue();
	 * 
	 * } catch (Exception e) { throw new
	 * JessaMapperException("Falha ao tentar converter valor:["+methodSetDestiny.
	 * getName()+"] espera ("+
	 * methodSetDestiny.getParameters()[0].getType().getSimpleName()+
	 * ") mas recebeu |"+ h.getValue()+"|"); } return b; }
	 */
	
	protected void setValue(T newValue) {
		value = newValue;
	}
	
	public T getValue() {		
		return value;
	}

}
