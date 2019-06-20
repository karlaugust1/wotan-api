package br.com.wotan.dtomapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import br.com.wotan.data.dto.HistoricoPerguntaDTO;
import br.com.wotan.data.model.HistoricoPergunta;

public class HistoricoPerguntaDTOMapper {
	
	private ModelMapper modelMapper;

	private PropertyMap<HistoricoPerguntaDTO, HistoricoPergunta> mappingDTO = new PropertyMap<HistoricoPerguntaDTO, HistoricoPergunta>() {
		protected void configure() {
			map().getAlternativa().setAlteId(source.getAlternativa());
			map().getEstudante().setEstuId(source.getEstudante());
			map().getPergunta().setPergId(source.getPergunta());
		}
	};

	public HistoricoPergunta fromDTO(HistoricoPerguntaDTO historicoPerguntaDTO) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addMappings(mappingDTO);
		return modelMapper.map(historicoPerguntaDTO, HistoricoPergunta.class);
	}
	
}
