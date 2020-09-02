package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Avaliacao;
import com.api.service.AvaliacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle das avaliações")
@RestController
@RequestMapping("/api/avaliacao")
public class AvaliacaoController {

	@Autowired
	AvaliacaoService service;
	
	@ApiOperation(value = "Cadastra uma avaliação.")
	@PostMapping()
	public Avaliacao create(@RequestBody Avaliacao avaliacao){
		return service.create(avaliacao);
	}
	
	@ApiOperation(value = "Atualiza uma avaliação.")
	@PutMapping()
	public Avaliacao update(@RequestBody Avaliacao avaliacao) {
		return service.update(avaliacao);
	}
	
	@ApiOperation(value = "Busca uma avaliação por código.")
	@GetMapping(value = "/{codigo}")
	public Avaliacao findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todas as avaliações.")
	@GetMapping("")
	public List<Avaliacao> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta uma avaliação por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
