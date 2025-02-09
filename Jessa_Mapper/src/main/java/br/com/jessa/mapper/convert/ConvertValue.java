package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertValue {
	ConvertValue() {
		throw new JessaMapperException(ExceptionsMessages.privateConstructor.getMessage());
	}
	 @SuppressWarnings("unchecked")
		public static HowToConvert toInt(Object b){
	        return new HowToConvert(b) {
	            @Override
	            public Integer newValue(Object valueToConvert) {
	                return Integer.valueOf(valueToConvert.toString());
	            }
	        };
	    }

	    public static HowToConvert toDouble(Object val){
	        return new HowToConvert(val) {
	            @SuppressWarnings("unchecked")
				@Override
	            public Double newValue(Object valueToConvert){
	                return Double.valueOf(valueToConvert.toString().replace(",","."));
	            }
	        };
	    }

	    public static HowToConvert toString(Object val){
	        return new HowToConvert(val) {
	            @SuppressWarnings("unchecked")
				@Override
	            public String newValue(Object valueToConvert){
	                return valueToConvert.toString();
	            }
	        };
	    }
}
