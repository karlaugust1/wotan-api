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
	private ProfessorDTO professor;
	private DisciplinaDTO disciplina;

}
