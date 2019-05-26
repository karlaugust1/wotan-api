package br.com.wotan.data.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProfessorDTO {

	private Long id;
	private UsuarioDTO usuario;
	private List<DisciplinaDTO> disciplinas;
	
}
