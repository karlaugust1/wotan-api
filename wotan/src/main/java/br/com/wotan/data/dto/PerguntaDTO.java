package br.com.wotan.data.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PerguntaDTO {

	private Long id;
	private String descricao;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataLimite;
	private Double valor;
	private Boolean ativa;
	private Boolean visivel;
	private ConteudoDTO conteudo;
	private List<AlternativaDTO> alternativas;
	private List<HistoricoPerguntaDTO> historicoPerguntas;
	
}
