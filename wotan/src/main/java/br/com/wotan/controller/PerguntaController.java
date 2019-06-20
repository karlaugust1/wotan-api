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

import br.com.wotan.data.dto.PerguntaDTO;
import br.com.wotan.service.PerguntaService;
import br.com.wotan.util.ServiceResponse;

@RestController
public class PerguntaController {

	@Autowired
	PerguntaService perguntaService;
	
	@PostMapping(value = "/perguntas", produces = "application/json")
	public ResponseEntity<ServiceResponse> insert(@RequestBody PerguntaDTO pergunta) {
		ServiceResponse result = perguntaService.insert(pergunta);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/perguntas", produces = "application/json")
	public ResponseEntity<ServiceResponse> update(@RequestBody PerguntaDTO pergunta) {
		ServiceResponse result = perguntaService.update(pergunta);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/perguntas", produces = "application/json")
	public ResponseEntity<ServiceResponse> delete(@RequestBody Long id) {
		ServiceResponse result = perguntaService.delete(id);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/perguntas/{id}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findById(@PathVariable Long id) {
		ServiceResponse result = perguntaService.findById(id);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/perguntas/professor/{id}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findByTeacher(@PathVariable Long id) {
		ServiceResponse result = perguntaService.findByTeacher(id);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/perguntas/disciplina/{id}/estudante/{idStudent}/{respondida}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findByDiscipline(@PathVariable Long id, @PathVariable Long idStudent, @PathVariable Boolean respondida) {
		ServiceResponse result = perguntaService.findByDiscipline(id, idStudent, respondida);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/perguntas/estudante/{id}/{respondida}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findAllQUestionsByStudent(@PathVariable Long id, @PathVariable Boolean respondida) {
		ServiceResponse result = perguntaService.findAllQUestionsByStudent(id, respondida);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}

}
