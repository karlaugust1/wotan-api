package br.com.wotan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AlternativaDTO {

	private Long id;
	private String descricao;
	private Boolean correta;
	private Long idPergunta;
	
}
