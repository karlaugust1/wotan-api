package br.com.wotan.data.enun;

public enum ExceptionType {

	ERROR("ERROR"),
	VALIDATION("VALIDATION"),
	SUCCESS("SUCCESS");
	
	private String value;
	
	ExceptionType(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
		
}
