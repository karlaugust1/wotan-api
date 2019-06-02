package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import br.com.wotan.data.dto.PerguntaDTO;
import br.com.wotan.data.model.Pergunta;

public class PerguntaDTOMapper {

	private ModelMapper modelMapper;
	private PropertyMap<Pergunta, PerguntaDTO> mapping = new PropertyMap<Pergunta, PerguntaDTO>() {
		protected void configure() {
			map().setId(source.getPergId());
			map().setIdConteudo(source.getConteudo().getContId());
			map().setTitulo(source.getConteudo().getContTitulo());
			map().setDataCriacao(source.getPergDataCriacao());
			map().setDataLimite(source.getPergDataLimite());
		}
	};
	private PropertyMap<PerguntaDTO, Pergunta> mappingDTO = new PropertyMap<PerguntaDTO, Pergunta>() {
		protected void configure() {
			map().setPergId(source.getId());
			map().getConteudo().setContId(source.getIdConteudo());
			map().getConteudo().setContTitulo(source.getTitulo());
			map().setPergDataCriacao(source.getDataCriacao());
			map().setPergDataLimite(source.getDataLimite());
			skip().getConteudo().getProfessor().getUsuario().setUsuaDataNascimento(null);
		}
	};

	public PerguntaDTO toDTO(Pergunta pergunta) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mapping);
		return modelMapper.map(pergunta, PerguntaDTO.class);
	}
	
	public List<PerguntaDTO> toDTO(List<Pergunta> perguntas) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mapping);
	    Type typeList = new TypeToken<List<PerguntaDTO>>() {}.getType();
		return modelMapper.map(perguntas, typeList);
	}

	public Pergunta fromDTO(PerguntaDTO perguntaDTO) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mappingDTO);
		return modelMapper.map(perguntaDTO, Pergunta.class);
	}

}
