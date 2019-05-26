package br.com.wotan.data.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class EstudanteDisciplina {
	
	private Boolean esdiTrancado;
	private LocalDateTime esdiDataTrancamento;
	private LocalDateTime esdiDataMatricula;
	private Estudante estudante;
	private Disciplina disciplina;

}
