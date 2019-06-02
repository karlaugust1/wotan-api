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

import br.com.wotan.data.dto.ConteudoDTO;
import br.com.wotan.service.ConteudoService;
import br.com.wotan.util.ServiceResponse;

@RestController
public class ConteudoController {

	@Autowired
	ConteudoService conteudoService;	
	
	@PostMapping(value = "/conteudos", produces = "application/json")
	public ResponseEntity<ServiceResponse> insert(@RequestBody ConteudoDTO conteudoDTO) {
		ServiceResponse result = conteudoService.insert(conteudoDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/conteudos", produces = "application/json")
	public ResponseEntity<ServiceResponse> listAll() {
		ServiceResponse result = conteudoService.listAll();
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/conteudos", produces = "application/json")
	public ResponseEntity<ServiceResponse> update(@RequestBody ConteudoDTO conteudoDTO) {
		ServiceResponse result = conteudoService.update(conteudoDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/conteudos/{id}", produces = "application/json")
	public ResponseEntity<ServiceResponse> findById(@PathVariable Long id) {
		ServiceResponse result = conteudoService.findById(id);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/conteudos", produces = "application/json")
	public ResponseEntity<ServiceResponse> delete(@RequestBody ConteudoDTO conteudoDTO) {
		ServiceResponse result = conteudoService.delete(conteudoDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}

}
