package br.com.wotan.data.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Professor {

	private Long profId;
	private Usuario usuario;
	private List<Disciplina> disciplinas;
	
}
