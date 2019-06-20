package br.com.wotan.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.HistoricoPerguntaDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.HistoricoPergunta;
import br.com.wotan.dtomapper.HistoricoPerguntaDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.HistoricoPerguntaRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class HistoricoPerguntaService {
	
	@Autowired
	HistoricoPerguntaRepository historicoPerguntaRepository;

	public ServiceResponse insert(HistoricoPerguntaDTO historicoPerguntaDTO) {
		
		HistoricoPergunta historicoPergunta = new HistoricoPerguntaDTOMapper().fromDTO(historicoPerguntaDTO);
		
		if(historicoPergunta.getHipeResposta() == null) {
			if(historicoPergunta.getAlternativa() == null) {
				throw new BusinessException(ExceptionType.VALIDATION, "Alternativa é obrigatório", "Alternativa é obrigatório");
			}else if(historicoPergunta.getAlternativa().getAlteId() == null) {
				throw new BusinessException(ExceptionType.VALIDATION, "Alternativa é obrigatório", "Alternativa é obrigatório");
			}
		} else if(historicoPergunta.getHipeResposta().equals("")) {
			throw new BusinessException(ExceptionType.VALIDATION, "Resposta é obrigatória", "Resposta é obrigatória");
		}
		
		if(historicoPergunta.getEstudante()	== null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do estudante é obrigatório", "Identificador do  estudante é obrigatório");
		} else if(historicoPergunta.getEstudante().getEstuId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do estudante é obrigatório", "Identificador do estudante é obrigatório");
		}
		
		if(historicoPergunta.getPergunta() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da pergunta é obrigatória", "Identificador da pergunta  é obrigatória");
		} else if(historicoPergunta.getPergunta().getPergId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da pergunta  é obrigatória", "Identificador da pergunta  é obrigatória");
		}
		
		historicoPergunta.setHipeDataRegistro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
		
		historicoPerguntaRepository.insert(historicoPergunta);
		
		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Histórico salvo com sucesso!", "Histórico salvo com sucesso!", null);

		return response;
		
	}
	
}
