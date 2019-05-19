package br.com.wotan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.CursoDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Curso;
import br.com.wotan.dtomapper.CursoDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.CursoRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class CursoService {
	
	@Autowired
	CursoRepository cursoRepository;
	
	/**
	 * @return Lista com todos os cursos cadastrados
	 */
	public ServiceResponse listAll() {
		
		List<Curso> cursos = cursoRepository.findAll();
		
		Map<String, Object> retornoObjeto = new HashMap<>();
		retornoObjeto.put("cursos", new CursoDTOMapper().setCursosToDTO(cursos));

		ServiceResponse negocio = new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", retornoObjeto);

		return negocio;
	}

	public ServiceResponse insert(CursoDTO cursoDTO) {
		
		Curso curso = new CursoDTOMapper().fromDTO(cursoDTO); 
		
		if(curso.getCursNome().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Nome do curso é obrigatório", "Nome do curso é obrigatório");
		}
		
		curso = cursoRepository.insert(curso);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("curso", new CursoDTOMapper().toDTO(curso));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Curso salvo com sucesso!", "Curso salvo com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse update(CursoDTO cursoDTO) {
		
		Curso curso = new CursoDTOMapper().fromDTO(cursoDTO); 
		
		if(curso.getCursNome().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Nome do curso é obrigatório", "Nome do curso é obrigatório");
		}
		
		if(curso.getCursId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do curso é obrigatório", "Identificador do curso é obrigatório");
		}
		
		curso = cursoRepository.update(curso);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("curso", new CursoDTOMapper().toDTO(curso));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Curso atualizado com sucesso!", "Curso atualizado com sucesso!", retornoObjeto);

		return response;
		
	}

	public ServiceResponse findById(Long id) {
		
		if(id == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do curso é obrigatório", "Identificador do curso é obrigatório");
		}
		
		Curso curso = cursoRepository.findById(id);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("curso", new CursoDTOMapper().toDTO(curso));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Curso obtido com sucesso!", "Curso obtido com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse delete(CursoDTO cursoDTO) {
		
		Curso curso = new CursoDTOMapper().fromDTO(cursoDTO); 
		
		if(curso.getCursId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do curso é obrigatório", "Identificador do curso é obrigatório");
		}
		
		cursoRepository.delete(curso);
		
		return new ServiceResponse(ExceptionType.SUCCESS, "Curso excluído com sucesso!", "Curso excluído com sucesso!", null);
	}

}

