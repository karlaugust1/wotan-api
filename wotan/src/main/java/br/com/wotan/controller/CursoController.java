package br.com.wotan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wotan.service.CursoService;
import br.com.wotan.util.ServiceResponse;

@RestController
public class CursoController {

	@Autowired
	CursoService cursoService;	
	
	@GetMapping(value = "/cursos", produces = "application/json")
	public ResponseEntity<ServiceResponse> listAll() {
		ServiceResponse result = cursoService.listAll();
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}

}
