package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertMapperValues {
	
	private ConvertMapperValues(){
		JessaMapperException.privateConstructor();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object tryToConvert(Object sourceElement, Class[] destinyElement) {
        if(sourceElement == null)return null;
        return  convert(sourceElement, destinyElement[0]);
    }


    public static <T> Object convert( Object sourceElement,  Class<T> aClass) {
        return new ConvertToPrimitive<T>(aClass)
                .tryTo(ConvertValue.toInt(sourceElement))
                .tryTo(ConvertValue.toDouble(sourceElement))
                .tryTo(ConvertValue.toString(sourceElement))
                .tryTo(ConvertEnum.toEnum(sourceElement,aClass))
                .getValue()
                ;
    }
}
