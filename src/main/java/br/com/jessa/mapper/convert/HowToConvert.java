package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.JessaMapperException;

public abstract class HowToConvert {
	 

		private Object value;
		
		protected HowToConvert(Object b) {			
			JessaMapperException.isNull(b);
			System.out.println("Class How:"+b.getClass().getSimpleName());
			value = b;
		}
		
		public Object getValue() {
			return value;
		}
		

		public abstract <T> T newValue(Object valueToConvert);

		@Override
		public String toString() {
			return "HowToConvert [value=" + value + "]";
		}
		
		

}
