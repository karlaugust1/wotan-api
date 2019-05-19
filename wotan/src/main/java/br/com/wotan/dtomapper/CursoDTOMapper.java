package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import br.com.wotan.data.dto.CursoDTO;
import br.com.wotan.data.model.Curso;

public class CursoDTOMapper {

	private ModelMapper modelMapper;	
	
	public List<CursoDTO> setCursosToDTO(List<Curso> cursos) {
		modelMapper = new ModelMapper();
	    Type typeList = new TypeToken<List<CursoDTO>>() {}.getType();
		return modelMapper.map(cursos, typeList);
	}
	
	public Curso fromDTO(CursoDTO cursoDTO) {
		modelMapper = new ModelMapper();
		return modelMapper.map(cursoDTO, Curso.class);
	}
	
	public CursoDTO toDTO(Curso curso) {
		modelMapper = new ModelMapper();
		return modelMapper.map(curso, CursoDTO.class);
	}
}
