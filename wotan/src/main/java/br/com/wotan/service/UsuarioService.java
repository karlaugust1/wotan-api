package br.com.wotan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.EstudanteDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Estudante;
import br.com.wotan.dtomapper.EstudanteDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.EstudanteRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class UsuarioService {
	
	@Autowired
	EstudanteRepository estudanteRepository;

	public ServiceResponse login(EstudanteDTO estudanteDTO) {
		
		if(estudanteDTO.getSenha().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Senha do estudante � obrigat�rio", "Senha do estudante � obrigat�rio");
		}
		
		if(estudanteDTO.getMatricula().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Matr�cula do estudante � obrigat�rio", "Matr�cula doa estudante � obrigat�rio");
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

}
