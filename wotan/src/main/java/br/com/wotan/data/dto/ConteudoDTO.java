package br.com.wotan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ConteudoDTO {
	
	private Long id;
	private String titulo;
	private String descricao;
	private Integer bimestre;
	private Long idProfessor;
	private Long idUsuario;
	private String nome;
	private Long idDisciplina;
	private String disciplina;

}
