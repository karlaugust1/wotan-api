package br.com.wotan.data.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PresencaDTO {

	private Long id;
	private String codigo;
	private LocalDateTime data;
	private LocalDateTime dataRegistro;
	private Boolean validado;
	private DisciplinaDTO disciplina;
	private EstudanteDTO estudante;
	private ProfessorDTO professor;
	
}
