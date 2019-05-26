package br.com.wotan.data.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Presenca {

	private Long presId;
	private String presCodigo;
	private LocalDateTime presData;
	private LocalDateTime presDataRegistro;
	private Boolean presValidado;
	private Disciplina disciplina;
	private Estudante estudante;
	private Professor professor;
	
}
