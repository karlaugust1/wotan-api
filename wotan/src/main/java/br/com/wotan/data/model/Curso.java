package br.com.wotan.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Curso {
	
	private Long cursId;
	private String cursNome;
	
	public Curso(Long cursId) {
		this.cursId = cursId;
	}
}
