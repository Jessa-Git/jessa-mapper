package br.com.jessa.mapper.convert;

import org.junit.Test;

import br.com.jessa.mapper.exception.JessaMapperException;

public class ConvertMapperValuesTest {

	@Test(expected = JessaMapperException.class)
	public void test() {
		new ConvertMapperValues();
	}

}
