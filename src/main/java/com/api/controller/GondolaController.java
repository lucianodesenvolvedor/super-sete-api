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

import com.api.entity.Gondola;
import com.api.service.GondolaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle das gondolas")
@RestController
@RequestMapping("/api/gondola")
public class GondolaController {

	@Autowired
	GondolaService service;
	
	@ApiOperation(value = "Cadastra uma gondola.")
	@PostMapping()
	public Gondola create(@RequestBody Gondola gondola){
		return service.create(gondola);
	}
	
	@ApiOperation(value = "Atualiza uma gondola.")
	@PutMapping()
	public Gondola update(@RequestBody Gondola avaliacao) {
		return service.update(avaliacao);
	}
	
	@ApiOperation(value = "Busca uma gondola por código.")
	@GetMapping(value = "/{codigo}")
	public Gondola findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todas as gondolas.")
	@GetMapping("")
	public List<Gondola> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta uma gondola por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
