package br.com.wotan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.ProfessorDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Professor;
import br.com.wotan.dtomapper.ProfessorDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.ProfessorRepository;
import br.com.wotan.repository.UsuarioRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class ProfessorService {
	
	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	public ServiceResponse insert(ProfessorDTO professorDTO) {
		
		Professor professor = new ProfessorDTOMapper().fromDTO(professorDTO); 
		
		if(professor.getUsuario() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Usuario do professor � obrigat�rio", "Usuario do professor � obrigat�rio");
		}
		
		if(professor.getUsuario().getUsuaNome().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Nome do professor � obrigat�rio", "Nome do professor � obrigat�rio");
		}
		
		if(professor.getUsuario().getUsuaCpf().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "CPF do professor � obrigat�rio", "CPF do professor � obrigat�rio");
		}
		
		if(professor.getUsuario().getUsuaRg().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "RG do professor � obrigat�rio", "RG do professor � obrigat�rio");
		}
		
		if(professor.getUsuario().getUsuaDataNascimento() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data de nascimento do professor � obrigat�rio", "Data de nascimento do professor � obrigat�rio");
		}
		
		professor.getUsuario().setUsuaSenha(professor.getUsuario().getUsuaRg());
		
		professor.setUsuario(usuarioRepository.insert(professor.getUsuario()));
		professor = professorRepository.insert(professor);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("professor", new ProfessorDTOMapper().toDTO(professor));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Professor salvo com sucesso!", "Professor salvo com sucesso!", retornoObjeto);

		return response;
		
	}

	public ServiceResponse update(ProfessorDTO professorDTO) {
		
		Professor professor = new ProfessorDTOMapper().fromDTO(professorDTO); 
		
		if(professor.getProfId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do professor � obrigat�rio", "Identificador do professor � obrigat�rio");
		}
		
		if(professor.getUsuario() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Usuario do professor � obrigat�rio", "Usuario do professor � obrigat�rio");
		}
		
		if(professor.getUsuario().getUsuaId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do usu�rio do professor � obrigat�rio", "Identificador do usu�rio do professor � obrigat�rio");
		}	
		
		if(professor.getUsuario().getUsuaNome().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Nome do professor � obrigat�rio", "Nome do professor � obrigat�rio");
		}
		
		if(professor.getUsuario().getUsuaCpf().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "CPF do professor � obrigat�rio", "CPF do professor � obrigat�rio");
		}
		
		if(professor.getUsuario().getUsuaRg().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "RG do professor � obrigat�rio", "RG do professor � obrigat�rio");
		}
		
		if(professor.getUsuario().getUsuaDataNascimento() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data de nascimento do professor � obrigat�rio", "Data de nascimento do professor � obrigat�rio");
		}
		
		professor.setUsuario(usuarioRepository.update(professor.getUsuario()));
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("professor", new ProfessorDTOMapper().toDTO(professor));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Professor atualizado com sucesso!", "Professor atualizado com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse findById(Long id) {
		
		if(id == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do professor � obrigat�rio", "Identificador do professor � obrigat�rio");
		}
		
		Professor professor = professorRepository.findById(id);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("professor", new ProfessorDTOMapper().toDTO(professor));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Professor obtido com sucesso!", "Professor obtido com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse findAll() {
		
		List<Professor> professores = professorRepository.findAll();
		
		Map<String, Object> retornoObjeto = new HashMap<>();
		retornoObjeto.put("cursos", new ProfessorDTOMapper().toDTO(professores));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", retornoObjeto);

		return response;
	}

	public ServiceResponse delete(ProfessorDTO professorDTO) {
		
		Professor professor = new ProfessorDTOMapper().fromDTO(professorDTO); 
		
		if(professor.getProfId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do professor � obrigat�rio", "Identificador do professor � obrigat�rio");
		}
		
		professorRepository.delete(professor);
		
		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Professor exclu�do com sucesso", "Professor exclu�do com sucesso", null);

		return response;
		
	}

}
