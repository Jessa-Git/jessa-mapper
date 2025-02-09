package br.com.jessa.mapper.reflection;

import java.lang.reflect.Method;

import br.com.jessa.mapper.MapperValidation;
import br.com.jessa.mapper.exception.JessaMapperException;

public class ReflectionObjectInvoke {
	private Method method;

    public ReflectionObjectInvoke(Method method) {
        this.method = method;
        JessaMapperException.isNull(method);
    }

    public Object invoke(Object instance)  {
        try {
            return method.invoke(instance);
        }catch (Exception e){
            JessaMapperException.invokeError(e,"Falha no reflection ao chamar GET para:",instance,method);
            return null;
        }
        
    }
    public void invoke(Object instance,Object ex)  {
        try {
            if(ex !=null)MapperValidation.invokeSetWithParameter(method,ex);
            method.invoke(instance,ex);
        }catch (Exception e){
            JessaMapperException.invokeError(e,"Falha no reflection ao chamar SET para:",instance,method);
        }
    }

}
