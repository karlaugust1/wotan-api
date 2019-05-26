package br.com.wotan.data.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class EstudanteDTO {
	
	private Long id;
	private String matricula;
	private LocalDateTime dataMatricula;
	private UsuarioDTO usuario;

}
