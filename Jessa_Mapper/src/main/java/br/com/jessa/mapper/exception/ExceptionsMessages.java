package br.com.jessa.mapper.exception;

public enum ExceptionsMessages {

	privateConstructor("Classe não pode criar objeto, utilizar apenas métodos staticos");
	private String message;
	private ExceptionsMessages(String msg) {
		this.message=msg;
	}
	public String getMessage() {
		return message;
	}
	
}
