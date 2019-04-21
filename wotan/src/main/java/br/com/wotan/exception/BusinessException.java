package br.com.wotan.exception;

import br.com.wotan.data.enun.ExceptionType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;
	private String details;
	private ExceptionType exceptionType;
	
	public BusinessException(Throwable throwable) {
		super(throwable);
	}
	
	public BusinessException(String message, Throwable throwable) {
		super(message, throwable);
	} 
	
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException( ExceptionType exceptionType, String message, String details) {
		super();
		this.message = message;
		this.details = details;
		this.exceptionType = exceptionType;
	} 
}
