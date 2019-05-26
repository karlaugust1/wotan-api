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
			throw new BusinessException(ExceptionType.VALIDATION, "Usuario do professor é obrigatório", "Usuario do professor é obrigatório");
		}
		
		if(professor.getUsuario().getUsuaNome().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Nome do professor é obrigatório", "Nome do professor é obrigatório");
		}
		
		if(professor.getUsuario().getUsuaCpf().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "CPF do professor é obrigatório", "CPF do professor é obrigatório");
		}
		
		if(professor.getUsuario().getUsuaRg().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "RG do professor é obrigatório", "RG do professor é obrigatório");
		}
		
		if(professor.getUsuario().getUsuaDataNascimento() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data de nascimento do professor é obrigatório", "Data de nascimento do professor é obrigatório");
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
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do professor é obrigatório", "Identificador do professor é obrigatório");
		}
		
		if(professor.getUsuario() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Usuario do professor é obrigatório", "Usuario do professor é obrigatório");
		}
		
		if(professor.getUsuario().getUsuaId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do usuário do professor é obrigatório", "Identificador do usuário do professor é obrigatório");
		}	
		
		if(professor.getUsuario().getUsuaNome().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Nome do professor é obrigatório", "Nome do professor é obrigatório");
		}
		
		if(professor.getUsuario().getUsuaCpf().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "CPF do professor é obrigatório", "CPF do professor é obrigatório");
		}
		
		if(professor.getUsuario().getUsuaRg().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "RG do professor é obrigatório", "RG do professor é obrigatório");
		}
		
		if(professor.getUsuario().getUsuaDataNascimento() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data de nascimento do professor é obrigatório", "Data de nascimento do professor é obrigatório");
		}
		
		professor.setUsuario(usuarioRepository.update(professor.getUsuario()));
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("professor", new ProfessorDTOMapper().toDTO(professor));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Professor atualizado com sucesso!", "Professor atualizado com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse findById(Long id) {
		
		if(id == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do professor é obrigatório", "Identificador do professor é obrigatório");
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
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do professor é obrigatório", "Identificador do professor é obrigatório");
		}
		
		professorRepository.delete(professor);
		
		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Professor excluído com sucesso", "Professor excluído com sucesso", null);

		return response;
		
	}

}
