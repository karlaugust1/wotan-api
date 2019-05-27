package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import br.com.wotan.data.dto.EstudanteDTO;
import br.com.wotan.data.model.Estudante;

public class EstudanteDTOMapper {
	
	private ModelMapper modelMapper = new ModelMapper();

	public Estudante fromDTO(EstudanteDTO estudanteDTO) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(estudanteDTO, Estudante.class);
	}
	
	public EstudanteDTO toDTO(Estudante estudante) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(estudante, EstudanteDTO.class);
	}
	
	public List<EstudanteDTO> toDTO(List<Estudante> estudantes) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    Type typeList = new TypeToken<List<EstudanteDTO>>() {}.getType();
		return modelMapper.map(estudantes, typeList);
	}

}
