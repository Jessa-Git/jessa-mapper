package br.com.jessa.mapper.exception;

public enum ExceptionsMessages {

	PRIVATE_CONSTRUCTOR("Classe não pode criar objeto, utilizar apenas métodos staticos"),
	CONVERT_VALUES("Falha ao tentar converter valor:[|1|] espera (|2|) mas recebeu [|3|]");
	private String message;
	private ExceptionsMessages(String msg) {
		this.message=msg;
	}
	public String getMessage() {
		return message;
	}
	public String getMessage(Object... args) {
		for(int i=0;i<args.length;i++) {
			message=message.replace("|"+(i+1)+"|", args[i].toString());
		}
		return message;
	}
	
}
