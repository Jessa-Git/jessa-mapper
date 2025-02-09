package br.com.jessa.mapper.process.obj;

public class DetectClass {

	public static Boolean isPrimitive(Class<?> val) {
		Boolean test;
		DetectClass d = new DetectClass();
		test= Boolean.logicalOr(d.detectString(val), d.detectInteger(val));
		test= Boolean.logicalOr(test, d.detectDouble(val));
		test= Boolean.logicalOr(test, d.detectDouble(val));
		test= Boolean.logicalOr(test, d.detectLong(val));
		test= Boolean.logicalOr(test, d.detectEnum(val));
		return test;		
	}
	private Boolean detectString(Class<?> val) {
		return val.equals(String.class);
	}
	private Boolean detectInteger(Class<?> val) {
		return val.equals(Integer.class);
	}
	private Boolean detectDouble(Class<?> val) {
		return val.equals(Double.class);
	}
	private Boolean detectLong(Class<?> val) {
		return val.equals(Long.class);
	}
	private Boolean detectEnum(Class<?> val) {
		return val.isEnum();
	}
	
}
