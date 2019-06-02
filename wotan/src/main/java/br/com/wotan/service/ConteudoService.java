package br.com.wotan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.ConteudoDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Conteudo;
import br.com.wotan.dtomapper.ConteudoDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.ConteudoRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class ConteudoService {
	
	@Autowired
	ConteudoRepository conteudoRepository;
	
	/**
	 * @return Lista com todos os conteudos cadastrados
	 */
	public ServiceResponse listAll() {
		
		List<Conteudo> conteudos = conteudoRepository.findAll();
		
		Map<String, Object> retornoObjeto = new HashMap<>();
		retornoObjeto.put("conteudos", new ConteudoDTOMapper().toDTO(conteudos));

		ServiceResponse negocio = new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", retornoObjeto);

		return negocio;
	}

	public ServiceResponse insert(ConteudoDTO conteudoDTO) {
		
		Conteudo conteudo = new ConteudoDTOMapper().fromDTO(conteudoDTO); 
		
		if(conteudo.getContTitulo().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "T�tulo do conteudo � obrigat�rio", "T�tulo do conteudo � obrigat�rio");
		}
		
		if(conteudo.getContDescricao().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Descri��o do conteudo � obrigat�rio", "Descri��o do conteudo � obrigat�rio");
		}
		
		if(conteudo.getContBimestre() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Bimestre do conteudo � obrigat�rio", "Bimestre do conteudo � obrigat�rio");
		} else if(conteudo.getContBimestre() < 0 ) {
			throw new BusinessException(ExceptionType.VALIDATION, "Bimestre do conteudo � obrigat�rio", "Bimestre do conteudo � obrigat�rio");
		}
		
		
		if(conteudo.getDisciplina() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Disciplina do conteudo � obrigat�rio", "Disciplina do conteudo � obrigat�rio");
		} else if(conteudo.getDisciplina().getDiscId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Disciplina do conteudo � obrigat�rio", "Disciplina do conteudo � obrigat�rio");
		}
		
		if(conteudo.getProfessor() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Professor do conteudo � obrigat�rio", "Professor do conteudo � obrigat�rio");
		} else if (conteudo.getProfessor().getProfId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Professor do conteudo � obrigat�rio", "Professor do conteudo � obrigat�rio");
		}
		 
		conteudo = conteudoRepository.insert(conteudo);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("conteudo", new ConteudoDTOMapper().toDTO(conteudo));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Conteudo salvo com sucesso!", "Conteudo salvo com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse update(ConteudoDTO conteudoDTO) {
		
		Conteudo conteudo = new ConteudoDTOMapper().fromDTO(conteudoDTO); 
		
		if(conteudo.getContId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do conteudo � obrigat�rio", "Identificador do conteudo � obrigat�rio");
		}
		
		if(conteudo.getContTitulo().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "T�tulo do conteudo � obrigat�rio", "T�tulo do conteudo � obrigat�rio");
		}
		
		if(conteudo.getContDescricao().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Descri��o do conteudo � obrigat�rio", "Descri��o do conteudo � obrigat�rio");
		}
		
		if(conteudo.getContBimestre() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Bimestre do conteudo � obrigat�rio", "Bimestre do conteudo � obrigat�rio");
		} else if(conteudo.getContBimestre() < 0 ) {
			throw new BusinessException(ExceptionType.VALIDATION, "Bimestre do conteudo � obrigat�rio", "Bimestre do conteudo � obrigat�rio");
		}
		
		
		if(conteudo.getDisciplina() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Disciplina do conteudo � obrigat�rio", "Disciplina do conteudo � obrigat�rio");
		} else if(conteudo.getDisciplina().getDiscId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Disciplina do conteudo � obrigat�rio", "Disciplina do conteudo � obrigat�rio");
		}
		
		conteudo = conteudoRepository.update(conteudo);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("conteudo", new ConteudoDTOMapper().toDTO(conteudo));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Conteudo atualizado com sucesso!", "Conteudo atualizado com sucesso!", retornoObjeto);

		return response;
		
	}

	public ServiceResponse findById(Long id) {
		
		if(id == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do conteudo � obrigat�rio", "Identificador do conteudo � obrigat�rio");
		}
		
		Conteudo conteudo = conteudoRepository.findById(id);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("conteudo", new ConteudoDTOMapper().toDTO(conteudo));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Conteudo obtido com sucesso!", "Conteudo obtido com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse delete(ConteudoDTO conteudoDTO) {
		
		Conteudo conteudo = new ConteudoDTOMapper().fromDTO(conteudoDTO); 
		
		if(conteudo.getContId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do conteudo � obrigat�rio", "Identificador do conteudo � obrigat�rio");
		}
		
		conteudoRepository.delete(conteudo);
		
		return new ServiceResponse(ExceptionType.SUCCESS, "Conteudo exclu�do com sucesso!", "Conteudo exclu�do com sucesso!", null);
	}

}
