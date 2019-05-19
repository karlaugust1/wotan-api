package br.com.wotan.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class Curso {
	
	private Long cursId;
	private String cursNome;
	
	public Curso(Long cursId) {
		this.cursId = cursId;
	}
}
