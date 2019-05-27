package br.com.wotan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DisciplinaDTO {

	private Long id;
	private String descricao;
	private Long idCurso;
	
}
