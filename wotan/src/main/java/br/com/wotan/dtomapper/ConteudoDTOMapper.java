package br.com.wotan.dtomapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import br.com.wotan.data.dto.ConteudoDTO;
import br.com.wotan.data.model.Conteudo;

public class ConteudoDTOMapper {

	private ModelMapper modelMapper;
	
	private PropertyMap<Conteudo, ConteudoDTO> mapping = new PropertyMap<Conteudo, ConteudoDTO>() {
		protected void configure() {
			map().setId(source.getContId());
			map().setIdDisciplina(source.getDisciplina().getDiscId());
			map().setIdProfessor(source.getProfessor().getProfId());
			map().setIdUsuario(source.getProfessor().getUsuario().getUsuaId());
			map().setNome(source.getProfessor().getUsuario().getUsuaNome());
			map().setDisciplina(source.getDisciplina().getDiscDescricao());
		}
	};
	private PropertyMap<ConteudoDTO, Conteudo> mappingDTO = new PropertyMap<ConteudoDTO, Conteudo>() {
		protected void configure() {
			map().setContId(source.getId());
			map().getDisciplina().setDiscId(source.getIdDisciplina());
			map().getProfessor().setProfId(source.getIdProfessor());
			map().getProfessor().getUsuario().setUsuaId(source.getIdUsuario());
			skip().getProfessor().getUsuario().setUsuaNome(null);
			skip().getDisciplina().getCurso().setCursNome(null);
			
		}
	};
	
	public List<ConteudoDTO> toDTO(List<Conteudo> conteudos) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mapping);
	    Type typeList = new TypeToken<List<ConteudoDTO>>() {}.getType();
		return modelMapper.map(conteudos, typeList);
	}
	
	public Conteudo fromDTO(ConteudoDTO conteudoDTO) {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mappingDTO);
		return modelMapper.map(conteudoDTO, Conteudo.class);
	}
	
	public ConteudoDTO toDTO(Conteudo conteudo) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mapping);
		return modelMapper.map(conteudo, ConteudoDTO.class);
	}
}
