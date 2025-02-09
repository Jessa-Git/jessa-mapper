package br.com.jessa.mapper.convert;

import java.lang.reflect.Method;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertMapperValues {
	
	private ConvertMapperValues(){
		JessaMapperException.privateConstructor();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object tryToConvert(Object sourceElement, Class[] destinyElement, Method mapperMethod) {
        if(sourceElement == null)return null;
        return  convert(sourceElement, destinyElement[0],mapperMethod);
    }


    public static <T> Object convert( Object sourceElement,  Class<T> aClass, Method mapperMethod) {
    	Object y= new ConvertToPrimitive<T>(aClass)
                .tryTo(ConvertValue.toInt(sourceElement))
                .tryTo(ConvertValue.toDouble(sourceElement))
                .tryTo(ConvertValue.toString(sourceElement))
                .tryTo(ConvertEnum.toEnum(sourceElement,aClass))
                .getValue()
                ;
    	if(y == null) {
    		y= ConvertToClass
    				.reMap(sourceElement,aClass, mapperMethod)
                    .getValue()
                    ;
    	}
    	
    	JessaMapperException.isNull(y);
    	return y;
    }
}
