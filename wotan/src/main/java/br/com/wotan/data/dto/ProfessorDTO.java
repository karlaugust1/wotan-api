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
public class ProfessorDTO {

	private Long id;
	private Long idUsuario;
	private String nome;
	private String cpf;
	private String rg;
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	@JsonSerialize(using = JsonDateTimeSerializer.class) 
	private LocalDateTime dataNascimento;
	private List<DisciplinaDTO> disciplinas;
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	@JsonSerialize(using = JsonDateTimeSerializer.class) 
	private LocalDateTime dataInicio;
	private String senha;
	
}
