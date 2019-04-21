package br.com.wotan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Curso;
import br.com.wotan.dtomapper.CursoDTOMapper;
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

}

