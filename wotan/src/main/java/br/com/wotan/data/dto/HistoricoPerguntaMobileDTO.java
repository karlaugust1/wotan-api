package br.com.wotan.data.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.wotan.converters.JsonDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoPerguntaMobileDTO {

	private String pergunta;
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private LocalDateTime dataCriacao;
	private String resposta;
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private LocalDateTime dataResposta;
	private Boolean correta;
}
