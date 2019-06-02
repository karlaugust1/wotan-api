package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import br.com.wotan.data.dto.EstudanteDTO;
import br.com.wotan.data.model.Estudante;

public class EstudanteDTOMapper {
	
	private ModelMapper modelMapper = new ModelMapper();
	private PropertyMap<Estudante, EstudanteDTO> mapping = new PropertyMap<Estudante, EstudanteDTO>() {
		protected void configure() {
			map().setId(source.getEstuId());
			map().setIdUsuario(source.getUsuario().getUsuaId());
			map().setMatricula(source.getEstuMatricula());
			map().setDataMatricula(source.getEstuDataMatricula());
		}
	};
	private PropertyMap<EstudanteDTO, Estudante> mappingDTO = new PropertyMap<EstudanteDTO, Estudante>() {
		protected void configure() {
			map().setEstuId(source.getId());
			map().getUsuario().setUsuaId(source.getIdUsuario());
			map().setEstuMatricula(source.getMatricula());
			map().setEstuDataMatricula(source.getDataMatricula());
		}
	};

	public Estudante fromDTO(EstudanteDTO estudanteDTO) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mappingDTO);
		return modelMapper.map(estudanteDTO, Estudante.class);
	}
	
	public List<Estudante> fromDTO(List<EstudanteDTO> estudantesDTO) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mappingDTO);
		Type typeList = new TypeToken<List<Estudante>>() {}.getType();
		return modelMapper.map(estudantesDTO, typeList);
	}
	
	public EstudanteDTO toDTO(Estudante estudante) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mapping);
		return modelMapper.map(estudante, EstudanteDTO.class);
	}
	
	public List<EstudanteDTO> toDTO(List<Estudante> estudantes) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mapping);
	    Type typeList = new TypeToken<List<EstudanteDTO>>() {}.getType();
		return modelMapper.map(estudantes, typeList);
	}

}
