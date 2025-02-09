package br.com.jessa.mapper.process;

import org.junit.Test;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ProcessMapObjectClassTest {

	@Test(expected = JessaMapperException.class)
	public void test() {
		new ProcessMapObjectClass();
	}

}
