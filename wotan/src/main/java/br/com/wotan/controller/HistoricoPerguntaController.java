package br.com.wotan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wotan.data.dto.HistoricoPerguntaDTO;
import br.com.wotan.service.HistoricoPerguntaService;
import br.com.wotan.util.ServiceResponse;

@RestController
public class HistoricoPerguntaController {

	@Autowired
	HistoricoPerguntaService historicoPerguntaService;

	@PostMapping(value = "/historico-pergunta", produces = "application/json")
	public ResponseEntity<ServiceResponse> insert(@RequestBody HistoricoPerguntaDTO historicoPerguntaDTO) {
		ServiceResponse result = historicoPerguntaService.insert(historicoPerguntaDTO);
		return new ResponseEntity<ServiceResponse>(result, HttpStatus.OK);
	}

}
