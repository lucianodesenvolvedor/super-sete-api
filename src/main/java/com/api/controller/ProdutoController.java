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

import com.api.entity.Produto;
import com.api.service.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle dos produtos")
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	ProdutoService service;
	
	@ApiOperation(value = "Cadastra um produto.")
	@PostMapping()
	public Produto create(@RequestBody Produto produto){
		return service.create(produto);
	}
	
	@ApiOperation(value = "Atualiza um produto.")
	@PutMapping()
	public Produto update(@RequestBody Produto produto) {
		return service.update(produto);
	}
	
	@ApiOperation(value = "Busca um produto por código.")
	@GetMapping(value = "/{codigo}")
	public Produto findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os produtos por codigo da gondola.")
	@GetMapping("/gondola/{codigo}")
	public List<Produto> findByGondolaCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findByGondolaCodigo(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os produtos.")
	@GetMapping("")
	public List<Produto> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta um produto por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
