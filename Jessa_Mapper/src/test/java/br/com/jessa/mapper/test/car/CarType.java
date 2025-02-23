package br.com.jessa.mapper.test.car;

public enum CarType {
	CARI(1,"b"),SEDAN(2,"c"),FUSCA(3,"d");
	
	private Integer n;
	private String c;
	
	private CarType(Integer n,String c) {
		this.c=c;
		this.n=n;
	}

	public Integer getN() {
		return n;
	}

	public String getC() {
		return c;
	}
	
	

}
