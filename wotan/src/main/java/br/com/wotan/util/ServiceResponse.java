package br.com.wotan.util;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import br.com.wotan.data.enun.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ServiceResponse {
	
	private ExceptionType exceptionType;
	private String message;
	private String details;
	private Map<String, Object> object;
	
	@JsonAnyGetter
	public Map<String, Object> getObject() {
		return this.object;
	}
	
	public void setObject (Map<String, Object> object){
		this.object = object;
	}
	
}

