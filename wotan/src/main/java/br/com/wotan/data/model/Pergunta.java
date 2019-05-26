package br.com.wotan.data.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Pergunta {

	private Long pergId;
	private String pergDescricao;
	private LocalDateTime pergDataCriacao;
	private LocalDateTime pergDataLimite;
	private Double pergValor;
	private Boolean pergAtiva;
	private Boolean pergVisivel;
	private Boolean pergExcluida;
	private Conteudo conteudo;
	private List<Alternativa> alternativas;
	private List<HistoricoPergunta> historicoPerguntas;
	
}
