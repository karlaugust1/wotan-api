package br.com.wotan.data.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.wotan.converters.JsonDateTimeDeserializer;
import br.com.wotan.converters.JsonDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PerguntaDTO {

	private Long id;
	private String descricao;
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	@JsonSerialize(using = JsonDateTimeSerializer.class) 
	private LocalDateTime dataCriacao;
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	@JsonSerialize(using = JsonDateTimeSerializer.class) 
	private LocalDateTime dataLimite;
	private Double valor;
	private Boolean ativa;
	private Boolean visivel;
	private Long idConteudo;
	private String titulo;
	private List<AlternativaDTO> alternativas;
	private List<HistoricoPerguntaDTO> historicoPerguntas;
	
}
