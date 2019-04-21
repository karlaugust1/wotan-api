package br.com.wotan.util;

import java.util.Map;

import br.com.wotan.data.enun.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ServiceResponse {
	
	private ExceptionType exceptionType;
	private String message;
	private String details;
	private Map<String, Object> object;
	
}

