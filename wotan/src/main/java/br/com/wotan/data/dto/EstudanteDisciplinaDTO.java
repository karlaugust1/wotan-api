package br.com.wotan.data.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class EstudanteDisciplinaDTO {
	
	private Boolean trancado;
	private LocalDateTime dataTrancamento;
	private LocalDateTime dataMatricula;
	private EstudanteDTO estudante;
	private DisciplinaDTO disciplina;

}
