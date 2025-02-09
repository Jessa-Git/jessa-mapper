package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertToPrimitive<T> extends ConvertModel<T> {
	private final Class<T> aClass;
	
	private boolean hasConvertValue;

	public ConvertToPrimitive(Class<T> aClass) {
		this.aClass = aClass;
		this.hasConvertValue = false;
	}

	public ConvertToPrimitive<T> tryTo(HowToConvert d) {
	
		if (hasConvertValue)
			return this;
		if (!isEnumConvert(d) && !isReturnClassEqualsDestinyClass(d)) {
			return this;
		}
		setValue(doThis(d)); 
		hasConvertValue = true;
		return this;
	}
	
	
	public ConvertToPrimitive<T> tryToClass(HowToConvert d) {		
		setValue(doThis(d)); 
		hasConvertValue = true;
		return this;
	}

	private boolean isReturnClassEqualsDestinyClass(HowToConvert d) {
		return aClass.equals(getClassReturnNewValue(d));
	}

	private boolean isEnumConvert(HowToConvert d) {
		return aClass.isEnum() && Enum.class.equals(getClassReturnNewValue(d));
	}

	

	private static Class<?> getClassReturnNewValue(HowToConvert d) {
		try {
			return d.getClass().getDeclaredMethod("newValue", Object.class).getReturnType();
		} catch (Exception e) {
			throw new JessaMapperException("Falha ao obter class de retorno de Como Obter Conversao (HowToConvert)");
		}
	}

	

}
