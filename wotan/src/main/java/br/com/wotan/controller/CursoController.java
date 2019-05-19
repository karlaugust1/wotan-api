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

import br.com.wotan.data.dto.CursoDTO;
import br.com.wotan.service.CursoService;
import br.com.wotan.util.ServiceResponse;

@RestController
public class CursoController {

	@Autowired
	CursoService cursoService;	
	
	@PostMapping(value = "/cursos", produces = "application/json")
	public ResponseEntity<ServiceResponse> insert(@RequestBody CursoDTO cursoDTO) {
		ServiceResponse result = cursoService.insert(cursoDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/cursos", produces = "application/json")
	public ResponseEntity<ServiceResponse> listAll() {
		ServiceResponse result = cursoService.listAll();
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/cursos", produces = "application/json")
	public ResponseEntity<ServiceResponse> update(@RequestBody CursoDTO cursoDTO) {
		ServiceResponse result = cursoService.update(cursoDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/cursos/{id}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findById(@PathVariable Long id) {
		ServiceResponse result = cursoService.findById(id);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/cursos", produces = "application/json")
	public ResponseEntity<ServiceResponse> delete(@RequestBody CursoDTO cursoDTO) {
		ServiceResponse result = cursoService.delete(cursoDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}

}
