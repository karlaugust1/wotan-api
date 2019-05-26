package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import br.com.wotan.data.dto.ProfessorDTO;
import br.com.wotan.data.model.Professor;

public class ProfessorDTOMapper {
	
	private ModelMapper modelMapper = new ModelMapper();

	public Professor fromDTO(ProfessorDTO professorDTO) {
		modelMapper = new ModelMapper();
		return modelMapper.map(professorDTO, Professor.class);
	}
	
	public ProfessorDTO toDTO(Professor professor) {
		modelMapper = new ModelMapper();
		return modelMapper.map(professor, ProfessorDTO.class);
	}
	
	public List<ProfessorDTO> toDTO(List<Professor> professores) {
		modelMapper = new ModelMapper();
	    Type typeList = new TypeToken<List<ProfessorDTO>>() {}.getType();
		return modelMapper.map(professores, typeList);
	}
	
}
