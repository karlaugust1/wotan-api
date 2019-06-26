package br.com.wotan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wotan.data.dto.EstudanteDTO;
import br.com.wotan.data.dto.ProfessorDTO;
import br.com.wotan.service.UsuarioService;
import br.com.wotan.util.ServiceResponse;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;

	@PostMapping(value = "/usuarios/estudante/login", produces = "application/json")
	public ResponseEntity<ServiceResponse> insert(@RequestBody EstudanteDTO estudanteDTO) {
		ServiceResponse result = usuarioService.login(estudanteDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/usuarios/professores/login", produces = "application/json")
	public ResponseEntity<ServiceResponse> insert(@RequestBody ProfessorDTO professorDTO) {
		ServiceResponse result = usuarioService.login(professorDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
}
