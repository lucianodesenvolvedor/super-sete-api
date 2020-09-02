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

import com.api.entity.PromocaoProduto;
import com.api.service.PromocaoProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle dos produtos da prmoção")
@RestController
@RequestMapping("/api/promocao_produto")
public class PromocaoProdutoController {

	@Autowired
	PromocaoProdutoService service;
	
	@ApiOperation(value = "Cadastra um produto na promoção.")
	@PostMapping()
	public PromocaoProduto create(@RequestBody PromocaoProduto promocaoProduto){
		return service.create(promocaoProduto);
	}
	
	@ApiOperation(value = "Atualiza um produto na promoção.")
	@PutMapping()
	public PromocaoProduto update(@RequestBody PromocaoProduto promocaoProduto) {
		return service.update(promocaoProduto);
	}
	
	@ApiOperation(value = "Busca um produto na promoção por código.")
	@GetMapping(value = "/{codigo}")
	public PromocaoProduto findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os produto na promoção pelo código da promoção.")
	@GetMapping("/promocao/{codigo}")
	public List<PromocaoProduto> findByPromocaoCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findByPromocaoCodigo(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os produto na promoção.")
	@GetMapping("")
	public List<PromocaoProduto> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta um produto na promoção por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
