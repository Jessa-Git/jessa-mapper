package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertEnum {
	
	private ConvertEnum() {
		JessaMapperException.privateConstructor();
	}
	
	public static HowToConvert toEnum(Object e,Class enumClass) {
		return new HowToConvert(e) {
			
			@Override
			public Enum newValue(Object valueToConvert) {
				return Enum.valueOf(enumClass, e.toString());
			}
		};
		 
	}

}
