package br.com.wotan.data.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String cpf;
	private String rg;
	private LocalDateTime dataNascimento;

}
