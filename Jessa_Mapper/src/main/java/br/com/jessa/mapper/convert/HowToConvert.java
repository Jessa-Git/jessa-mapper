package br.com.jessa.mapper.convert;

import br.com.jessa.mapper.exception.JessaMapperException;

public abstract class HowToConvert {
	 

		private Object value;
		
		protected HowToConvert(Object b) {
			JessaMapperException.isNull(b);
			value = b;
		}
		
		public Object getValue() {
			return value;
		}
		

		public abstract <T> T newValue(Object valueToConvert);

}
