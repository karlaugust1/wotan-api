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
public class EstudanteDTO {
	
	private Long id;
	private String matricula;
	@JsonDeserialize(using = JsonDateTimeDeserializer.class) 
	@JsonSerialize(using = JsonDateTimeSerializer.class) 
	private LocalDateTime dataMatricula;
	private Long idUsuario;
	private String nome;
	private String cpf;
	private String rg;
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	@JsonSerialize(using = JsonDateTimeSerializer.class) 
	private LocalDateTime dataNascimento;

}
