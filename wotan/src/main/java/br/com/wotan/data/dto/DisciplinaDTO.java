package br.com.wotan.data.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DisciplinaDTO {

	private Long id;
	private String descricao;
	private Long idCurso;
	private String curso;
	private List<EstudanteDTO> estudantes;
	private List<ProfessorDTO> professores;
	
}
