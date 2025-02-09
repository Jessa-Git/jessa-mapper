package br.com.jessa.mapper.convert;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertEnumTest {

	@Test(expected = JessaMapperException.class)
	public void test() {
		new ConvertEnum();
	}

}
