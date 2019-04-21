package br.com.wotan.exception;

import br.com.wotan.data.enun.ExceptionType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BusinessResponse {
	
	private ExceptionType exceptionType;
	private String message;
	private String details;
	private String date;
	
	public BusinessResponse(ExceptionType exceptionType, String message, String details, String date) {
		super();
		this.exceptionType = exceptionType;
		this.message = message;
		this.details = details;
		this.date = date;
	}
}