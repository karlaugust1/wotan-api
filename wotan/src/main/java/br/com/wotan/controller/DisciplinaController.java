package br.com.wotan.controller;

import java.util.List;

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

import br.com.wotan.data.dto.DisciplinaDTO;
import br.com.wotan.data.dto.EstudanteDTO;
import br.com.wotan.data.dto.ProfessorDTO;
import br.com.wotan.service.DisciplinaService;
import br.com.wotan.util.ServiceResponse;

@RestController
public class DisciplinaController {
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@PostMapping(value = "/disciplinas", produces = "application/json")
	public ResponseEntity<ServiceResponse> insert(@RequestBody DisciplinaDTO disciplinaDTO) {
		ServiceResponse result = disciplinaService.insert(disciplinaDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/disciplinas", produces = "application/json")
	public ResponseEntity<ServiceResponse> update(@RequestBody DisciplinaDTO disciplinaDTO) {
		ServiceResponse result = disciplinaService.update(disciplinaDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/disciplinas/{id}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findById(@PathVariable Long id) {
		ServiceResponse result = disciplinaService.findById(id);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/disciplinas", produces = "application/json")
	public ResponseEntity<ServiceResponse> findAll() {
		ServiceResponse result = disciplinaService.findAll();
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/disciplinas", produces = "application/json")
	public ResponseEntity<ServiceResponse> delete(@RequestBody DisciplinaDTO disciplinaDTO) {
		ServiceResponse result = disciplinaService.delete(disciplinaDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/disciplinas/{id}/vincular-estudantes", produces = "application/json")
	public ResponseEntity<ServiceResponse> linkStudends( @PathVariable Long id, @RequestBody List<EstudanteDTO> estudantes) {
		ServiceResponse result = disciplinaService.linkStudends(id, estudantes);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/disciplinas/{id}/vincular-professores", produces = "application/json")
	public ResponseEntity<ServiceResponse> linkTeachers( @PathVariable Long id, @RequestBody List<ProfessorDTO> professores) {
		ServiceResponse result = disciplinaService.linkTeachers(id, professores);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
}
