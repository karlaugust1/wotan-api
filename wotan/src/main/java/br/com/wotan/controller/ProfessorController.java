package br.com.wotan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wotan.data.dto.ProfessorDTO;
import br.com.wotan.service.ProfessorService;
import br.com.wotan.util.ServiceResponse;

@RestController
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	
	@PostMapping(value = "/professores", produces = "application/json")
	public ResponseEntity<ServiceResponse> insert(@RequestBody ProfessorDTO professor) {
		ServiceResponse result = professorService.insert(professor);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/professores", produces = "application/json")
	public ResponseEntity<ServiceResponse> update(@RequestBody ProfessorDTO professor) {
		ServiceResponse result = professorService.update(professor);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/professores/{id}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findById(@PathVariable Long id) {
		ServiceResponse result = professorService.findById(id);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/professores", produces = "application/json")
	public ResponseEntity<ServiceResponse> findAll() {
		ServiceResponse result = professorService.findAll();
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/professores", produces = "application/json")
	public ResponseEntity<ServiceResponse> delete(@RequestBody ProfessorDTO professor) {
		ServiceResponse result = professorService.delete(professor);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
}
