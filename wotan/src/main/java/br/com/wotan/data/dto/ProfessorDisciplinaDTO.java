package br.com.wotan.data.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProfessorDisciplinaDTO {
	
	private ProfessorDTO professor;
	private DisciplinaDTO disciplina;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

}
