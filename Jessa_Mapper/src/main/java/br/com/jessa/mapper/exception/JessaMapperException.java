package br.com.jessa.mapper.exception;

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
		return "[Mapper error] - " + msg;
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
            s = s+"("+instance.getClass().getSimpleName()+")"+instance.toString()+" Method:"+m;
        }else{
            s = s+" [Instancia NULL]";
        }
        throw new JessaMapperException(e.getMessage()+"["+s+"]");
    }
	public static void methodWithMoreParameterThanOne(String s, Class<?>[] parameterTypes) {
        if(parameterTypes.length>1)throw new JessaMapperException(s);
    }

}
