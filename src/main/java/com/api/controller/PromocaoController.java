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

import com.api.entity.Promocao;
import com.api.service.PromocaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle das promoções")
@RestController
@RequestMapping("/api/promocao")
public class PromocaoController {

	@Autowired
	PromocaoService service;
	
	@ApiOperation(value = "Cadastra uma promoção.")
	@PostMapping()
	public Promocao create(@RequestBody Promocao promocao){
		return service.create(promocao);
	}
	
	@ApiOperation(value = "Atualiza um promoção.")
	@PutMapping()
	public Promocao update(@RequestBody Promocao promocao) {
		return service.update(promocao);
	}
	
	@ApiOperation(value = "Busca uma promoção por código.")
	@GetMapping(value = "/{codigo}")
	public Promocao findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Retorna uma lista com todas as promoções.")
	@GetMapping("")
	public List<Promocao> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta uma promoção por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
