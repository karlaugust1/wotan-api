package br.com.wotan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.DisciplinaDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Disciplina;
import br.com.wotan.dtomapper.DisciplinaDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.DisciplinaRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class DisciplinaService {
	
	@Autowired
	DisciplinaRepository disciplinaRepository;

	public ServiceResponse insert(DisciplinaDTO disciplinaDTO) {
		
		Disciplina disciplina = new DisciplinaDTOMapper().fromDTO(disciplinaDTO); 
		
		if(disciplina.getDiscDescricao().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Descri��o da disciplina � obrigat�rio", "Descri��o da disciplina � obrigat�rio");
		}
		
		if(disciplina.getCurso() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Curso da disciplina � obrigat�rio", "Curso da disciplina � obrigat�rio");
		} else if (disciplina.getCurso().getCursId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Curso da disciplina � obrigat�rio", "Curso da disciplina � obrigat�rio");
		}
		
		disciplina = disciplinaRepository.insert(disciplina);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("disciplina", new DisciplinaDTOMapper().toDTO(disciplina));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Disciplina salva com sucesso!", "Disciplina salva com sucesso!", retornoObjeto);

		return response;
		
	}

	public ServiceResponse update(DisciplinaDTO disciplinaDTO) {
		
		Disciplina disciplina = new DisciplinaDTOMapper().fromDTO(disciplinaDTO); 
		
		if(disciplina.getDiscId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da disciplina � obrigat�rio", "Identificador da disciplina � obrigat�rio");
		}
		
		if(disciplina.getDiscDescricao().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Descri��o da disciplina � obrigat�rio", "Descri��o da disciplina � obrigat�rio");
		}
		
		if(disciplina.getCurso() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Curso da disciplina � obrigat�rio", "Curso da disciplina � obrigat�rio");
		} else if (disciplina.getCurso().getCursId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Curso da disciplina � obrigat�rio", "Curso da disciplina � obrigat�rio");
		}
		
		disciplina = disciplinaRepository.update(disciplina);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("disciplina", new DisciplinaDTOMapper().toDTO(disciplina));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Disciplina atualizada com sucesso!", "Disciplina atualizada com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse findById(Long id) {
		
		if(id == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da disciplina � obrigat�rio", "Identificador da disciplina � obrigat�rio");
		}
		
		Disciplina disciplina = disciplinaRepository.findById(id);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("disciplina", new DisciplinaDTOMapper().toDTO(disciplina));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Disciplina obtida com sucesso!", "Disciplina obtida com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse findAll() {
		
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		
		Map<String, Object> retornoObjeto = new HashMap<>();
		retornoObjeto.put("disciplinas", new DisciplinaDTOMapper().toDTO(disciplinas));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", retornoObjeto);

		return response;
	}

	public ServiceResponse delete(DisciplinaDTO disciplinaDTO) {
		
		Disciplina disciplina = new DisciplinaDTOMapper().fromDTO(disciplinaDTO); 
		
		if(disciplina.getDiscId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador da disciplina � obrigat�rio", "Identificador da disciplina � obrigat�rio");
		}
		
		disciplinaRepository.delete(disciplina);
		
		return new ServiceResponse(ExceptionType.SUCCESS, "Disciplina exclu�da com sucesso!", "Disciplina exclu�da com sucesso!", null);
	}

}
