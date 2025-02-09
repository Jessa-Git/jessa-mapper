package br.com.jessa.mapper;

import java.lang.reflect.Proxy;

import br.com.jessa.mapper.exception.JessaMapperException;

public class JessaMapper {
	private  JessaMapper() {}

	@SuppressWarnings("unchecked")
	public static <T extends ToInterfaceMapper> T implement(Class<T> interfaceMapper) {
		Object mapGeneric = null;
		mapGeneric = Proxy.newProxyInstance(
				interfaceMapper.getClassLoader(), 
				new Class[] { interfaceMapper },
				new MapperHandler());
		
		JessaMapperException.isNull(mapGeneric);

		return (T) mapGeneric;
	}
}
