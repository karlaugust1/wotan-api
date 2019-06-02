package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import br.com.wotan.data.dto.ProfessorDTO;
import br.com.wotan.data.model.Professor;

public class ProfessorDTOMapper {
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<Professor> fromDTO(List<ProfessorDTO> professoresDTO) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Type typeList = new TypeToken<List<Professor>>() {}.getType();
		return modelMapper.map(professoresDTO, typeList);
	}

	public Professor fromDTO(ProfessorDTO professorDTO) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(professorDTO, Professor.class);
	}
	
	public ProfessorDTO toDTO(Professor professor) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(professor, ProfessorDTO.class);
	}
	
	public List<ProfessorDTO> toDTO(List<Professor> professores) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    Type typeList = new TypeToken<List<ProfessorDTO>>() {}.getType();
		return modelMapper.map(professores, typeList);
	}
	
}
