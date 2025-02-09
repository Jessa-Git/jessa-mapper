package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertEnum {
	
	ConvertEnum() {
		throw new JessaMapperException(ExceptionsMessages.privateConstructor.getMessage());
	}
	
	public static HowToConvert toEnum(Object e,Class enumClass) {
		return new HowToConvert(e) {
			
			@Override
			public Enum newValue(Object valueToConvert) {
				System.out.println("Enum "+enumClass.getConstructors().length);
				return Enum.valueOf(enumClass, valueToConvert.toString());
			}
		};
		 
	}

}
