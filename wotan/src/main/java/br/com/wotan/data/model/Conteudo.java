package br.com.wotan.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Conteudo {
	
	private Long contId;
	private String contTitulo;
	private String contDescricao;
	private Integer contBimestre;
	private Professor professor;
	private Disciplina disciplina;

}
