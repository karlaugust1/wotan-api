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

import br.com.wotan.data.dto.EstudanteDTO;
import br.com.wotan.service.EstudanteService;
import br.com.wotan.util.ServiceResponse;

@RestController
public class EstudanteController {
	
	@Autowired
	EstudanteService estudanteService;
	
	@PostMapping(value = "/estudantes", produces = "application/json")
	public ResponseEntity<ServiceResponse> insert(@RequestBody EstudanteDTO estudanteDTO) {
		ServiceResponse result = estudanteService.insert(estudanteDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/estudantes", produces = "application/json")
	public ResponseEntity<ServiceResponse> update(@RequestBody EstudanteDTO estudanteDTO) {
		ServiceResponse result = estudanteService.update(estudanteDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/estudantes/{id}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findById(@PathVariable Long id) {
		ServiceResponse result = estudanteService.findById(id);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/estudantes", produces = "application/json")
	public ResponseEntity<ServiceResponse> findAll() {
		ServiceResponse result = estudanteService.findAll();
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/estudantes", produces = "application/json")
	public ResponseEntity<ServiceResponse> delete(@RequestBody EstudanteDTO estudanteDTO) {
		ServiceResponse result = estudanteService.delete(estudanteDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/estudantes-sem-vinculo", produces = "application/json")
	public ResponseEntity<ServiceResponse> findStudentsWithNoLink() {
		ServiceResponse result = estudanteService.findStudentsWithNoLink();
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/estudantes/disciplina/{id}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findStudentsWithLink(@PathVariable Long id) {
		ServiceResponse result = estudanteService.findStudentsWithLink(id);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}

}
