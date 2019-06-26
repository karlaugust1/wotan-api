package br.com.wotan.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.HistoricoPerguntaDTO;
import br.com.wotan.data.dto.HistoricoPerguntaMobileDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.HistoricoPergunta;
import br.com.wotan.data.model.Pergunta;
import br.com.wotan.dtomapper.HistoricoPerguntaDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.HistoricoPerguntaRepository;
import br.com.wotan.repository.PerguntaRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class HistoricoPerguntaService {
	
	@Autowired
	HistoricoPerguntaRepository historicoPerguntaRepository;
	@Autowired
	PerguntaRepository perguntaRepository;
	
	public ServiceResponse insert(HistoricoPerguntaDTO historicoPerguntaDTO) {
		
		HistoricoPergunta historicoPergunta = new HistoricoPerguntaDTOMapper().fromDTO(historicoPerguntaDTO);
		
		if(historicoPergunta.getHipeResposta() == null) {
			if(historicoPergunta.getAlternativa() == null) {
				throw new BusinessException(ExceptionType.VALIDATION, "Alternativa � obrigat�rio", "Alternativa � obrigat�rio");
			}else if(historicoPergunta.getAlternativa().getAlteId() == null) {
				throw new BusinessException(ExceptionType.VALIDATION, "Alternativa � obrigat�rio", "Alternativa � obrigat�rio");
			}
		} else if(historicoPergunta.getHipeResposta().equals("")) {
			throw new BusinessException(ExceptionType.VALIDATION, "Resposta � obrigat�ria", "Resposta � obrigat�ria");
		}
		
		if(historicoPergunta.getEstudante()	== null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do estudante � obrigat�rio", "Identificador do  estudante � obrigat�rio");
		} else if(historicoPergunta.getEstudante().getEstuId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do estudante � obrigat�rio", "Identificador do estudante � obrigat�rio");
		}
		
		if(historicoPergunta.getPergunta() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da pergunta � obrigat�ria", "Identificador da pergunta  � obrigat�ria");
		} else if(historicoPergunta.getPergunta().getPergId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da pergunta  � obrigat�ria", "Identificador da pergunta  � obrigat�ria");
		}
		
		historicoPergunta.setHipeDataRegistro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
		
		historicoPerguntaRepository.insert(historicoPergunta);
		
		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Hist�rico salvo com sucesso!", "Hist�rico salvo com sucesso!", null);

		return response;
		
	}
	
	public ServiceResponse correct(HistoricoPerguntaDTO historicoPerguntaDTO) {
		
		HistoricoPergunta historicoPergunta = new HistoricoPerguntaDTOMapper().fromDTO(historicoPerguntaDTO);
	
		if(historicoPergunta.getEstudante()	== null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do estudante � obrigat�rio", "Identificador do  estudante � obrigat�rio");
		} else if(historicoPergunta.getEstudante().getEstuId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do estudante � obrigat�rio", "Identificador do estudante � obrigat�rio");
		}
		
		if(historicoPergunta.getPergunta() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da pergunta � obrigat�ria", "Identificador da pergunta  � obrigat�ria");
		} else if(historicoPergunta.getPergunta().getPergId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da pergunta  � obrigat�ria", "Identificador da pergunta  � obrigat�ria");
		}
		
		if(historicoPergunta.getHipeAcertou() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Informar se acertou ou n�o � obrigat�ria", "Informar se acertou ou n�o � obrigat�ria");
		}
		
		historicoPerguntaRepository.correct(historicoPergunta);
		
		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Hist�rico salvo com sucesso!", "Hist�rico salvo com sucesso!", null);

		return response;
		
	}

	public ServiceResponse findQuestionHistoricByStudent(Long id) {
		
		if(id == null || id <= 0) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do estudante � obrigat�rio", "Identificador do estudante � obrigat�rio");
		}
		
		List<Pergunta> perguntas = perguntaRepository.findAnswerdQuerionsByStudent(id);
		
		perguntas.forEach(pergunta -> {
			pergunta.setHistoricoPerguntas(historicoPerguntaRepository.findByAnsweredQuestionByStudent(pergunta.getPergId(), id));
		});
		
		List<HistoricoPerguntaMobileDTO> historico = new ArrayList<>();
		
		for (Pergunta pergunta : perguntas) {
			HistoricoPerguntaMobileDTO h = new HistoricoPerguntaMobileDTO();
			h.setCorreta(pergunta.getHistoricoPerguntas().get(0).getHipeAcertou());
			h.setDataCriacao(pergunta.getPergDataCriacao());
			h.setDataResposta(pergunta.getHistoricoPerguntas().get(0).getHipeDataRegistro());
			h.setPergunta(pergunta.getPergDescricao());
			if(pergunta.getHistoricoPerguntas().get(0).getAlternativa().getAlteId() == null) {
				h.setResposta(pergunta.getHistoricoPerguntas().get(0).getHipeResposta());				
			} else {
				h.setResposta(pergunta.getHistoricoPerguntas().get(0).getAlternativa().getAlteDescricao());
			}
			historico.add(h);
		}
		
		Map<String, Object> objetoRetorno = new HashMap<>();
		
		objetoRetorno.put("historico", historico);
		
		return new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", objetoRetorno);
	}

	
}
