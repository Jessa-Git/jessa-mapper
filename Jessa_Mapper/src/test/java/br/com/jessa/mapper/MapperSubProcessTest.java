package br.com.jessa.mapper;

import org.junit.Test;

import br.com.jessa.mapper.exception.JessaMapperException;

public class MapperSubProcessTest {

	@Test(expected = JessaMapperException.class)
	public void test() {
			new MapperSubProcess();
	}

}
