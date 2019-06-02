package br.com.wotan.data.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.wotan.converters.JsonDateTimeDeserializer;
import br.com.wotan.converters.JsonDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class HistoricoPerguntaDTO {
	
	private Boolean acertou;
	private String resposta;
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private LocalDateTime dataRegistro;
	private EstudanteDTO estudante;
	private PerguntaDTO pergunta;

}
