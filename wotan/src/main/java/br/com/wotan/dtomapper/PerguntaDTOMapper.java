package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import br.com.wotan.data.dto.PerguntaDTO;
import br.com.wotan.data.model.Pergunta;

public class PerguntaDTOMapper {

	private ModelMapper modelMapper;

	public PerguntaDTO toDTO(Pergunta pergunta) {
		modelMapper = new ModelMapper();
		return modelMapper.map(pergunta, PerguntaDTO.class);
	}
	
	public List<PerguntaDTO> toDTO(List<Pergunta> perguntas) {
		modelMapper = new ModelMapper();
	    Type typeList = new TypeToken<List<PerguntaDTO>>() {}.getType();
		return modelMapper.map(perguntas, typeList);
	}

	public Pergunta fromDTO(PerguntaDTO perguntaDTO) {
		modelMapper = new ModelMapper();
		return modelMapper.map(perguntaDTO, Pergunta.class);
	}

}
