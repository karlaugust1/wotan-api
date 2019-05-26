package br.com.wotan.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Disciplina {

	private Long discId;
	private String discDescricao;
	private Curso curso;
	
}
