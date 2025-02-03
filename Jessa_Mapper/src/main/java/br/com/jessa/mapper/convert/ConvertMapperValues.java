package br.com.jessa.mapper.convert;

public class ConvertMapperValues {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object tryToConvert(Object sourceElement, Class[] destinyElement) {
        if(sourceElement == null)return null;
        Object valueResultConversion = sourceElement;
        valueResultConversion = convert(sourceElement, destinyElement[0]);
        return valueResultConversion;
    }


    public static <T> Object convert( Object sourceElement,  Class<T> aClass) {
        return new ConvertToPrimitive<T>(aClass)
                .tryTo(ConvertValue.toInt(sourceElement))
                .tryTo(ConvertValue.toDouble(sourceElement))
                .tryTo(ConvertValue.toString(sourceElement))
                .getValue()
                ;
    }
}
