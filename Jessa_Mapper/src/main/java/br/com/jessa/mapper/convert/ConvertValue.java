package br.com.jessa.mapper.convert;

public class ConvertValue {
	 @SuppressWarnings("unchecked")
		public static HowToConvert toInt(Object b){
	        return new HowToConvert(b) {
	            @Override
	            public Integer newValue(Object valueToConvert) {
	                return Integer.valueOf(valueToConvert.toString());
	            }
	        };
	    }

	    public static HowToConvert toDouble(Object val){
	        return new HowToConvert(val) {
	            @SuppressWarnings("unchecked")
				@Override
	            public Double newValue(Object valueToConvert){
	                return Double.valueOf(valueToConvert.toString().replace(",","."));
	            }
	        };
	    }

	    public static HowToConvert toString(Object val){
	        return new HowToConvert(val) {
	            @SuppressWarnings("unchecked")
				@Override
	            public String newValue(Object valueToConvert){
	                return valueToConvert.toString();
	            }
	        };
	    }
}
