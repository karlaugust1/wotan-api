package br.com.wotan.data.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProfessorDisciplina {
	
	private Professor professor;
	private Disciplina disciplina;
	private LocalDateTime prdiDataInicio;
	private LocalDateTime prdiDataFim;

}
