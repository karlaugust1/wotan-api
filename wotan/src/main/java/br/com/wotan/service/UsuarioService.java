package br.com.wotan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.EstudanteDTO;
import br.com.wotan.data.dto.ProfessorDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Estudante;
import br.com.wotan.data.model.Professor;
import br.com.wotan.dtomapper.EstudanteDTOMapper;
import br.com.wotan.dtomapper.ProfessorDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.EstudanteRepository;
import br.com.wotan.repository.ProfessorRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class UsuarioService {
	
	@Autowired
	EstudanteRepository estudanteRepository;
	@Autowired
	ProfessorRepository professorRepository;

	public ServiceResponse login(EstudanteDTO estudanteDTO) {
		
		if(estudanteDTO.getSenha().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Senha do estudante é obrigatório", "Senha do estudante é obrigatório");
		}
		
		if(estudanteDTO.getMatricula().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Matrícula do estudante é obrigatório", "Matrícula do estudante é obrigatório");
		}
		
		Estudante estudante = estudanteRepository.findStudentByRegisterNumber(estudanteDTO.getMatricula());
		
		if(!estudante.getUsuario().getUsuaSenha().equals(estudanteDTO.getSenha())) {			
			return new ServiceResponse(ExceptionType.SUCCESS, "Senha incorreta!", "Senha incorreta!", null);
		}		
			
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("estudante", new EstudanteDTOMapper().toDTO(estudante));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Login realizado com sucesso!", "Login realizado com sucesso!", retornoObjeto);

		return response;
	}
	
	public ServiceResponse login(ProfessorDTO professorDTO) {
		
		if(professorDTO.getSenha().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Senha do professor é obrigatório", "Senha do professor é obrigatório");
		}
		
		if(professorDTO.getCpf().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Matrícula do professor é obrigatório", "Matrícula do professor é obrigatório");
		}
		
		Professor professor = professorRepository.findTeacherByCPF(professorDTO.getCpf());
		
		
		if(professor == null) {
			
			ServiceResponse response = new ServiceResponse(ExceptionType.ERROR, "Falha ao realizar o login!", "Falha ao realizar login!", null);
			return response;
			
		} else { 
			Map<String, Object> retornoObjeto = new HashMap<String, Object>();
			
			if(!professor.getUsuario().getUsuaSenha().equals(professorDTO.getSenha()))	
				return new ServiceResponse(ExceptionType.SUCCESS, "Senha incorreta!", "Senha incorreta!", null);
			
			retornoObjeto.put("professor", new ProfessorDTOMapper().toDTO(professor));
			
			ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Login realizado com sucesso!", "Login realizado com sucesso!", retornoObjeto);
			
			return response;
		}		
			
	}

}
