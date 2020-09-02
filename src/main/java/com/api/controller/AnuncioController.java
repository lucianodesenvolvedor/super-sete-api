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

import com.api.entity.Anuncio;
import com.api.service.AnuncioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle dos anuncios")
@RestController
@RequestMapping("/api/anuncio")
public class AnuncioController {

	@Autowired
	AnuncioService service;
	
	@ApiOperation(value = "Cadastra um anuncio.")
	@PostMapping()
	public Anuncio create(@RequestBody Anuncio anuncio){
		return service.create(anuncio);
	}
	
	@ApiOperation(value = "Atualiza um anuncio.")
	@PutMapping()
	public Anuncio update(@RequestBody Anuncio anuncio) {
		return service.update(anuncio);
	}
	
	@ApiOperation(value = "Busca um anuncio por código.")
	@GetMapping(value = "/{codigo}")
	public Anuncio findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os anuncio.")
	@GetMapping("")
	public List<Anuncio> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta um anuncio por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
