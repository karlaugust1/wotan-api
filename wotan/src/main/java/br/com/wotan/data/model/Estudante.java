package br.com.wotan.data.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Estudante {
	
	private Long estuId;
	private String estuMatricula;
	private LocalDateTime estuDataMatricula;
	private Usuario usuario;

}
