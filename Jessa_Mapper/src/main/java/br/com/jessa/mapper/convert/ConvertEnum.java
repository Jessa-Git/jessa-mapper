package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertEnum {
	
	ConvertEnum() {
		throw new JessaMapperException(ExceptionsMessages.PRIVATE_CONSTRUCTOR.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	public static HowToConvert toEnum(Object e,Class enumClass) {
		return new HowToConvert(e) {
			
			@SuppressWarnings({ "unchecked" })
			@Override
			public Enum newValue(Object valueToConvert) {
				return Enum.valueOf(enumClass, valueToConvert.toString());
			}
		};
		 
	}

}
