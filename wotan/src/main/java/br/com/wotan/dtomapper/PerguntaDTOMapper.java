package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import br.com.wotan.data.dto.PerguntaDTO;
import br.com.wotan.data.model.Pergunta;

public class PerguntaDTOMapper {

	private ModelMapper modelMapper;

	public PerguntaDTO toDTO(Pergunta pergunta) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(pergunta, PerguntaDTO.class);
	}
	
	public List<PerguntaDTO> toDTO(List<Pergunta> perguntas) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    Type typeList = new TypeToken<List<PerguntaDTO>>() {}.getType();
		return modelMapper.map(perguntas, typeList);
	}

	public Pergunta fromDTO(PerguntaDTO perguntaDTO) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(perguntaDTO, Pergunta.class);
	}

}
