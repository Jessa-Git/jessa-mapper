package br.com.jessa.mapper.exception;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JessaMapperException extends RuntimeException {

	private static final long serialVersionUID = -1311370250134138200L;

	public JessaMapperException(String string) {
		super(mensagem(string));
	}

	public static void isNull(Object mapGeneric) {
		if(mapGeneric ==null)
			throw new JessaMapperException("Objeto NULL");
	}

	protected static String mensagem(String msg) {
		return "\n[Mapper error] - " + msg;
	}

	public static void checkSourceObject(Object[] args) {
		if (args == null)
			throw new JessaMapperException(" Erro no Mapper: Dados de entrada NULL");
		if (args[0] == null)
			throw new JessaMapperException(" Erro no Mapper: Objeto de Entrada NULL");

	}
	
	public static void out(Exception e, String msg) {
        throw new JessaMapperException(msg + " --->>> ERROR LOG[ " + e.getMessage() + " ]");
    }
	
	public static void invokeError(Exception e, String s, Object instance, Method getMethod) {
        String m = "No Method";
        if(getMethod!=null){
            m = ""+getMethod.getName();
        }
        if(instance !=null){
            s = s+"("+instance.getClass().getSimpleName()+")"+" Method:"+m;
        }else{
            s = s+" [Instancia NULL]";
        }
        throw new JessaMapperException("Erro "+s+"\n"+e.getMessage());
        }
	public static void methodWithMoreParameterThanOne(String s, Class<?>[] parameterTypes) {
        if(parameterTypes.length>1)throw new JessaMapperException(s);
    }

	public static void privateConstructor() {
		throw new JessaMapperException("Classe não pode criar objeto, utilizar apenas métodos staticos");
		
	}

	public static void protectStackOverFlowByClass(Class<? extends Object> reference,Field field ) {
		 Class<?> fieldClass = field.getType();
		if(reference.equals(fieldClass))
			throw new JessaMapperException("Over Flow Protect: Class "+reference.getSimpleName()+ " contains field ["+fieldClass.getSimpleName()+" "+field.getName()+"] with same class");
		
	}

}
