package br.com.wotan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wotan.data.dto.EstudanteDTO;
import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Estudante;
import br.com.wotan.dtomapper.EstudanteDTOMapper;
import br.com.wotan.exception.BusinessException;
import br.com.wotan.repository.EstudanteRepository;
import br.com.wotan.repository.UsuarioRepository;
import br.com.wotan.util.ServiceResponse;

@Service
public class EstudanteService {
	
	@Autowired
	EstudanteRepository estudanteRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	public ServiceResponse insert(EstudanteDTO estudanteDTO) {
		
		Estudante estudante = new EstudanteDTOMapper().fromDTO(estudanteDTO); 
		
		if(estudante.getUsuario() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Usuario do estudante � obrigat�rio", "Usuario do estudante � obrigat�rio");
		}
		
		if(estudante.getUsuario().getUsuaNome().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Nome do estudante � obrigat�rio", "Nome do estudante � obrigat�rio");
		}
		
		if(estudante.getUsuario().getUsuaCpf().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "CPF do estudante � obrigat�rio", "CPF do estudante � obrigat�rio");
		}
		
		if(estudante.getUsuario().getUsuaRg().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "RG do estudante � obrigat�rio", "RG do estudante � obrigat�rio");
		}	
		
		if(estudante.getUsuario().getUsuaDataNascimento() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data de nascimento do estudante � obrigat�rio", "Data de estudante do professor � obrigat�rio");
		}
		
		if(estudante.getEstuMatricula().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Matr�cula do estudante � obrigat�rio", "Matr�cula do estudante � obrigat�rio");
		}
		
		if(estudante.getEstuDataMatricula() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data de matr�cula do estudante � obrigat�rio", "Data de matr�cula do estudante � obrigat�rio");
		}
		
		
		estudante.getUsuario().setUsuaSenha(estudante.getUsuario().getUsuaRg());
		
		estudante.setUsuario(usuarioRepository.insert(estudante.getUsuario()));
		estudante = estudanteRepository.insert(estudante);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("estudante", new EstudanteDTOMapper().toDTO(estudante));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Estudante salvo com sucesso!", "Estudante salvo com sucesso!", retornoObjeto);

		return response;
		
	}

	public ServiceResponse update(EstudanteDTO estudanteDTO) {
		
		Estudante estudante = new EstudanteDTOMapper().fromDTO(estudanteDTO); 
		
		if(estudante.getUsuario() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Usuario do estudante � obrigat�rio", "Usuario do estudante � obrigat�rio");
		}
		
		if(estudante.getUsuario().getUsuaNome().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Nome do estudante � obrigat�rio", "Nome do estudante � obrigat�rio");
		}
		
		if(estudante.getUsuario().getUsuaCpf().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "CPF do estudante � obrigat�rio", "CPF do estudante � obrigat�rio");
		}
		
		if(estudante.getUsuario().getUsuaRg().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "RG do estudante � obrigat�rio", "RG do estudante � obrigat�rio");
		}
		
		if(estudante.getUsuario().getUsuaDataNascimento() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data de nascimento do estudante � obrigat�rio", "Data de estudante do professor � obrigat�rio");
		}
		
		if(estudante.getEstuMatricula().isEmpty()) {
			throw new BusinessException(ExceptionType.VALIDATION, "Matr�cula do estudante � obrigat�rio", "Matr�cula do estudante � obrigat�rio");
		}
		
		if(estudante.getEstuDataMatricula() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Data de matr�cula do estudante � obrigat�rio", "Data de matr�cula do estudante � obrigat�rio");
		}
		
		if(estudante.getUsuario().getUsuaId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do usu�rio do estudante � obrigat�rio", "Identificador do usu�rio do estudante � obrigat�rio");
		}
		
		if(estudante.getEstuId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do usu�rio do estudante � obrigat�rio", "Identificador do usu�rio do estudante � obrigat�rio");
		}
		
		estudante.setUsuario(usuarioRepository.update(estudante.getUsuario()));
		estudante = estudanteRepository.update(estudante);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("estudante", new EstudanteDTOMapper().toDTO(estudante)); 

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Estudante atualizado com sucesso!", "Estudante atualizado com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse findById(Long id) {
		
		if(id == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do estudante � obrigat�rio", "Identificador do estudante � obrigat�rio");
		}
		
		Estudante estudante = estudanteRepository.findById(id);
		
		Map<String, Object> retornoObjeto = new HashMap<String, Object>();
		
		retornoObjeto.put("estudante", new EstudanteDTOMapper().toDTO(estudante));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Estudante obtido com sucesso!", "Estudante obtido com sucesso!", retornoObjeto);

		return response;
	}

	public ServiceResponse findAll() {
		
		List<Estudante> estudantes = estudanteRepository.findAll();
		
		Map<String, Object> retornoObjeto = new HashMap<>();
		retornoObjeto.put("estudantes", new EstudanteDTOMapper().toDTO(estudantes));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", retornoObjeto);

		return response;
	}

	public ServiceResponse delete(EstudanteDTO estudanteDTO) {
		
		Estudante estudante = new EstudanteDTOMapper().fromDTO(estudanteDTO); 
		
		if(estudante.getEstuId() == null) {
			throw new BusinessException(ExceptionType.VALIDATION, "Identificador do estudante � obrigat�rio", "Identificador do estudante � obrigat�rio");
		}
		
		estudanteRepository.delete(estudante);
		
		return new ServiceResponse(ExceptionType.SUCCESS, "Estudante exclu�do com sucesso!", "Estudante exclu�do com sucesso!", null);
	}

	public ServiceResponse findStudentsWithNoLink() {
		
		List<Estudante> estudantes = estudanteRepository.findStudentsWithNoLink();
		
		Map<String, Object> retornoObjeto = new HashMap<>();
		retornoObjeto.put("estudantes", new EstudanteDTOMapper().toDTO(estudantes));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", retornoObjeto);

		return response;
	}
	
	public ServiceResponse findStudentsWithLink(Long id) {
		
		List<Estudante> estudantes = estudanteRepository.findStudentsWithLink(id);
		
		Map<String, Object> retornoObjeto = new HashMap<>();
		retornoObjeto.put("estudantes", new EstudanteDTOMapper().toDTO(estudantes));

		ServiceResponse response = new ServiceResponse(ExceptionType.SUCCESS, "Consulta realizada com sucesso", "Consulta realizada com sucesso", retornoObjeto);

		return response;
	}

}
