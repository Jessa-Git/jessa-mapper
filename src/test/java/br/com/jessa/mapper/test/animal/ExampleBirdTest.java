package br.com.jessa.mapper.test.animal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExampleBirdTest {

	@Test
	public void testBirdConvert() {
		ConvertBird convert = ConvertBird.INSTANCE;
		
		Bird bird= new Bird();
		bird.setAge(1);
		bird.setName("Kiwi");
		
		WebBird webBird =  convert.convertBirdToWebBird(bird);
		
		assertEquals(bird.getAge()+"", webBird.getBirdAge());
		assertEquals(bird.getName(), webBird.getName());
		
	}
}
