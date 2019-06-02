package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import br.com.wotan.data.dto.DisciplinaDTO;
import br.com.wotan.data.model.Disciplina;

public class DisciplinaDTOMapper {
	
	private ModelMapper modelMapper = new ModelMapper();
	private PropertyMap<Disciplina, DisciplinaDTO> mapping = new PropertyMap<Disciplina, DisciplinaDTO>() {
		protected void configure() {
			map().setIdCurso(source.getCurso().getCursId());
			map().setCurso(source.getCurso().getCursNome());
		}
	};
	private PropertyMap<DisciplinaDTO, Disciplina> mappingDTO = new PropertyMap<DisciplinaDTO, Disciplina>() {
		protected void configure() {
			map().getCurso().setCursId(source.getIdCurso());
		}
	};

	public Disciplina fromDTO(DisciplinaDTO disciplinaDTO) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mappingDTO);
		return modelMapper.map(disciplinaDTO, Disciplina.class);
	}
	
	public DisciplinaDTO toDTO(Disciplina disciplina) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mapping);
		return modelMapper.map(disciplina, DisciplinaDTO.class);
	}
	
	public List<DisciplinaDTO> toDTO(List<Disciplina> disciplinas) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mapping);	
	    Type typeList = new TypeToken<List<DisciplinaDTO>>() {}.getType();
		return modelMapper.map(disciplinas, typeList);
	}

}
