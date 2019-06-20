package br.com.wotan.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class HistoricoPerguntaDTO {
	
	private Boolean acertou;
	private String resposta;
	private Long estudante;
	private Long pergunta;
	private Long alternativa;

}
