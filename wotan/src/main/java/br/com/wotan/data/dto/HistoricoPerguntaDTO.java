package br.com.wotan.data.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class HistoricoPerguntaDTO {
	
	private Boolean acertou;
	private String resposta;
	private LocalDateTime dataRegistro;
	private EstudanteDTO estudante;
	private PerguntaDTO pergunta;

}
