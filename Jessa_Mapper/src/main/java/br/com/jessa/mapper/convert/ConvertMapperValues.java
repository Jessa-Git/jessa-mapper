package br.com.jessa.mapper.convert;

import java.lang.reflect.Method;

import br.com.jessa.mapper.exception.ExceptionsMessages;
import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertMapperValues {
	
	ConvertMapperValues(){
		throw new JessaMapperException(ExceptionsMessages.PRIVATE_CONSTRUCTOR.getMessage());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object tryToConvert(Object sourceElement, Method methodSetDestiny, Method mapperMethod,Method getSourceMethod) {
		Class[] destinyElement = methodSetDestiny.getParameterTypes();
        if(sourceElement == null)return null;
        return  convert(sourceElement, destinyElement[0],mapperMethod,methodSetDestiny,getSourceMethod);
    }


    public static <T> Object convert( Object sourceElement,  Class<T> aClass, Method mapperMethod,Method methodSetDestiny,Method getSourceMethod) {
    	Object y= new ConvertToPrimitive<T>(aClass,methodSetDestiny,getSourceMethod)
                .tryTo(ConvertValue.toInt(sourceElement))
                .tryTo(ConvertValue.toDouble(sourceElement))
                .tryTo(ConvertValue.toString(sourceElement))
                .tryTo(ConvertEnum.toEnum(sourceElement,aClass))
                .getValue()
                ;
    	if(y == null) {
    		y= ConvertToClass
    				.reMap(sourceElement,aClass, mapperMethod)
    				.newValue(sourceElement)
                    ;
    	}
    	JessaMapperException.isNull(y);
    	return y;
    }

}
