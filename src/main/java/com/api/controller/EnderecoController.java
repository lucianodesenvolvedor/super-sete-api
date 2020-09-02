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

import com.api.entity.Endereco;
import com.api.service.EnderecoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle dos endereços")
@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService service;
	
	@ApiOperation(value = "Cadastra um endereco.")
	@PostMapping()
	public Endereco create(@RequestBody Endereco endereco){
		return service.create(endereco);
	}
	
	@ApiOperation(value = "Atualiza um endereco.")
	@PutMapping()
	public Endereco update(@RequestBody Endereco endereco) {
		return service.update(endereco);
	}
	
	@ApiOperation(value = "Busca um endereco por código.")
	@GetMapping(value = "/{codigo}")
	public Endereco findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os endereços.")
	@GetMapping("")
	public List<Endereco> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta um endereco por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
