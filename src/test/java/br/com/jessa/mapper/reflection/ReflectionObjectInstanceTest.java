package br.com.jessa.mapper.reflection;

import org.junit.Test;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ReflectionObjectInstanceTest {

	@Test(expected = JessaMapperException.class)
	public void test() {
		new ReflectionObjectInstance();
	}

}
