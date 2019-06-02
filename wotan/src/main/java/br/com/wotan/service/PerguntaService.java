package br.com.wotan.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.PerguntaDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Alternativa;
import br.com.wotan.data.model.Pergunta;
import br.com.wotan.dtomapper.PerguntaDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.AlternativaRepository;
import br.com.wotan.repository.PerguntaRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class PerguntaService {
	
	@Autowired
	PerguntaRepository perguntaRepository;
	@Autowired
	AlternativaRepository alternativaRepository;

	public ServiceResponse insert(PerguntaDTO perguntaDTO) {
		
		Pergunta pergunta = new PerguntaDTOMapper().fromDTO(perguntaDTO); 
		
		if(pergunta.getPergDescricao().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Descri��o da pergunta � obrigat�rio", "Descri��o da pergunta � obrigat�rio");
		}
		
		if(pergunta.getConteudo() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Disciplina da pergunta � obrigat�rio", "Disciplina da pergunta � obrigat�rio");
		} else if(pergunta.getConteudo().getContId() == null){
			throw new BusinessException(ExceptionType.VALIDATION, "Disciplina da pergunta � obrigat�rio", "Disciplina da pergunta � obrigat�rio");
		}
		
		if(pergunta.getPergDataLimite() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data limite da pergunta � obrigat�rio", "Data limite da pergunta � obrigat�rio");
		}
		
		if(pergunta.getPergValor() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Valor da pergunta � obrigat�rio", "Valor da pergunta � obrigat�rio");
		}
		
		pergunta.setPergDataCriacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))); //Hor�rio de Bras�lia
		pergunta.setPergAtiva(Boolean.TRUE);
		pergunta.setPergVisivel(Boolean.TRUE);
		
		pergunta = perguntaRepository.insert(pergunta);
		
		Boolean alternativaCorreta = false;
		for (Alternativa alternativa : pergunta.getAlternativas()) {
			if(alternativa.getAlteDescricao().isEmpty()) {
				throw new BusinessException(ExceptionType.VALIDATION, "Descri��o de uma alternativa � obrigat�ria", "Descri��o de uma alternativa � obrigat�ria");
			}
			if(alternativa.getAlteCorreta() != null)
				if(alternativa.getAlteCorreta())			
					alternativaCorreta = true;
		};
		
		if(!alternativaCorreta) {
			throw new BusinessException(ExceptionType.VALIDATION, "Ao menos uma alternativa correta � obrigat�rio", "Ao menos uma alternativa correta � obrigat�rio");
		}
		
		for (Alternativa alternativa : pergunta.getAlternativas()) {
			alternativa.setPergunta(pergunta);
			alternativaRepository.insert(alternativa);
		}
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("pergunta", new PerguntaDTOMapper().toDTO(pergunta));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Pergunta salva com sucesso!", "Pergunta salva com sucesso!", retornoObjeto);

		return response;
	}
	
	public ServiceResponse update(PerguntaDTO perguntaDTO) {
		
		Pergunta pergunta = new PerguntaDTOMapper().fromDTO(perguntaDTO); 
		
		if(pergunta.getPergDescricao().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Descri��o da pergunta � obrigat�rio", "Descri��o da pergunta � obrigat�rio");
		}
		
		if(pergunta.getConteudo() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Disciplina da pergunta � obrigat�rio", "Disciplina da pergunta � obrigat�rio");
		} else if(pergunta.getConteudo().getContId() == null){
			throw new BusinessException(ExceptionType.VALIDATION, "Disciplina da pergunta � obrigat�rio", "Disciplina da pergunta � obrigat�rio");
		}
		
		if(pergunta.getPergDataLimite() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data limite da pergunta � obrigat�rio", "Data limite da pergunta � obrigat�rio");
		}
		
		if(pergunta.getPergDataCriacao() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data de cria��o da pergunta � obrigat�rio", "Data de cria��o da pergunta � obrigat�rio");
		}
		
		if(pergunta.getPergValor() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Valor da pergunta � obrigat�rio", "Valor da pergunta � obrigat�rio");
		}
		
		if(pergunta.getPergAtiva() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Atributo Ativo da pergunta � obrigat�rio", "Atributo Ativo da pergunta � obrigat�rio");
		}
		
		if(pergunta.getPergVisivel() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Atributo Visivel da pergunta � obrigat�rio", "Atributo Visivel da pergunta � obrigat�rio");
		}		
				
		pergunta = perguntaRepository.update(pergunta);
		
		for (Alternativa alternativa : pergunta.getAlternativas()) {
			alternativaRepository.update(alternativa);
			
		}
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("pergunta", new PerguntaDTOMapper().toDTO(pergunta));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Pergunta atualizada com sucesso!", "Pergunta atualizada com sucesso!", retornoObjeto);

		return response;
	}
	
	public ServiceResponse delete(Long id) {
		
		
		if(id == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da pergunta � obrigat�rio", "Identificador da pergunta � obrigat�rio");
		}
		
		perguntaRepository.delete(id);
		
		return new ServiceResponse(ExceptionType.SUCCESS, "Pergunta exclu�da com sucesso!", "Pergunta exclu�da com sucesso!", null);
	}

	public ServiceResponse findByTeacher(Long id) {
		
		if(id == null || id <= 0) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do professor � obrigat�rio", "Identificador do professor � obrigat�rio");
		}
		
		List<Pergunta> perguntas = perguntaRepository.findByTeacher(id);
		
		perguntas.forEach(pergunta -> {
			pergunta.setAlternativas(alternativaRepository.findByQuestion(pergunta.getPergId()));
		});
		
		Map<String, Object> objetoRetorno = new HashMap<>();
		
		objetoRetorno.put("perguntas", new PerguntaDTOMapper().toDTO(perguntas));
		
		return new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", objetoRetorno);
		
	}

	public ServiceResponse findById(Long id) {
		if(id == null || id <= 0) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da pergunta � obrigat�rio", "Identificador da pergunta � obrigat�rio");
		}
		
		Pergunta pergunta = perguntaRepository.findById(id);
		
		pergunta.setAlternativas(alternativaRepository.findByQuestion(pergunta.getPergId()));
		
		Map<String, Object> objetoRetorno = new HashMap<>();
		
		objetoRetorno.put("pergunta", new PerguntaDTOMapper().toDTO(pergunta));
		
		return new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", objetoRetorno);
	}

}
