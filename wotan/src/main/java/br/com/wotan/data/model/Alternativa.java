package br.com.wotan.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Alternativa {

	private Long alteId;
	private String alteDescricao;
	private Boolean alteCorreta;
	private Pergunta pergunta;
	
}
