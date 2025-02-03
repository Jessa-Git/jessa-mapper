package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertToPrimitive <T>  {
	 private final Class<T> aClass;
	    private  T value;
	    private boolean hasConvertValue;

	    public ConvertToPrimitive(Class<T> aClass) {
	        this.aClass = aClass;
	        this.hasConvertValue = false;
	    }

	    public ConvertToPrimitive <T> tryTo(HowToConvert d){
	        if(hasConvertValue)return this;
	        Class<?> newValue = getClassReturnNewValue(d);
	        if(!aClass.equals(newValue))return this;
	         value =doThis(d);
	         hasConvertValue = true;
	        return this;
	    }
	    
	    private static <T> T doThis(HowToConvert h){
	        T b = null;
	        try {

	            b= h.newValue(h.getValue());
	            
	        }catch (Exception e){
	        	
	            JessaMapperException.out(e,"Falha ao tentar converter valor");
	        }
	        return b;
	    }

	    private static Class<?> getClassReturnNewValue(HowToConvert d) {
	        try {
	            return d.getClass().getDeclaredMethod("newValue",Object.class).getReturnType();
	        }catch (Exception e){
	            throw new JessaMapperException("Falha ao obter class de retorno de Como Obter Conversao (HowToConvert)");
	        }
	    }
	    public T getValue(){
	        JessaMapperException.isNull(value);
	        return  value;
	    }
	
}
