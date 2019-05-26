package br.com.wotan.data.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Usuario {
	
	private Long usuaId;
	private String usuaNome;
	private String usuaCpf;
	private String usuaRg;
	private String usuaSenha;
	private LocalDateTime usuaDataNascimento;

}
