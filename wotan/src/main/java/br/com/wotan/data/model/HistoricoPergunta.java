package br.com.wotan.data.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class HistoricoPergunta {
	
	private Boolean hipeAcertou;
	private String hipeResposta;
	private LocalDateTime hipeDataRegistro;
	private Estudante estudante;
	private Pergunta pergunta;
	private Alternativa alternativa;

}
