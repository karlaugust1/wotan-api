package br.com.wotan.data.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Disciplina {

	private Long discId;
	private String discDescricao;
	private Curso curso;
	List<Estudante> estudantes;
	List<Professor> professores;
	
	public Disciplina(Long id) {
		this.discId = id;
	}
	
}
