package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import br.com.wotan.data.dto.DisciplinaDTO;
import br.com.wotan.data.model.Disciplina;

public class DisciplinaDTOMapper {
	
	private ModelMapper modelMapper = new ModelMapper();

	public Disciplina fromDTO(DisciplinaDTO disciplinaDTO) {
		modelMapper = new ModelMapper();
		return modelMapper.map(disciplinaDTO, Disciplina.class);
	}
	
	public DisciplinaDTO toDTO(Disciplina disciplina) {
		modelMapper = new ModelMapper();
		return modelMapper.map(disciplina, DisciplinaDTO.class);
	}
	
	public List<DisciplinaDTO> toDTO(List<Disciplina> disciplinas) {
		modelMapper = new ModelMapper();
	    Type typeList = new TypeToken<List<DisciplinaDTO>>() {}.getType();
		return modelMapper.map(disciplinas, typeList);
	}

}
