package br.com.jessa.mapper;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.jessa.mapper.exception.JessaMapperException;

public class MapperValidationTest {

	@Test(expected = JessaMapperException.class)
	public void test() {
		new MapperValidation();
	}

}
