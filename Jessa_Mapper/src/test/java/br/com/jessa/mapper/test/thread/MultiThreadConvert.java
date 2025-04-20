package br.com.jessa.mapper.test.thread;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

public class MultiThreadConvert {
	@Test
    public void testMapperStringObjectInMultiThread(){
		ArrayList<Thread> o = new ArrayList<Thread>();
		for(int i=0;i<100000;i++)
			o.add(n(i));
		for(Thread s:o)
			s.start();
		for(Thread s:o)
			while(s.isAlive())continue;
	
			
	}
	
	private Thread n(int i) {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				PersonObj l = new PersonObj().generate();
				MultiThreadMapInterface in = MultiThreadMapInterface.INSTANCE;		
				PersonDTO j = in.toTestStringSourceMapToDestiny(l);
				Testi t =new Testi();
				t.setDto(j.getAgeString());
				t.setObj(l.getAge()+"");
				t.thred = i+"";
				t.test();
			}
		});
		
	}
	class Testi {
		private String dto;
		private String obj;
		private String thred;
		public String getDto() {
			return dto;
		}
		public void setDto(String dto) {
			this.dto = dto;
		}
		public String getObj() {
			return obj;
		}
		public void setObj(String obj) {
			this.obj = obj;
		}
		public void test() {
			assertNotNull(obj);
			assertNotNull(dto);
			assertEquals("Thread["+thred+"]: ",obj+"", dto);
		}
	}
}
