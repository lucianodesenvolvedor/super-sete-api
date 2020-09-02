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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Compra;
import com.api.entity.model.RelatorioCompraTicketMedio;
import com.api.repository.custom.CompraCustomRepository;
import com.api.service.CompraService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle dos compras")
@RestController
@RequestMapping("/api/compra")
public class CompraController {

	@Autowired
	CompraService service;
	
	@Autowired
	CompraCustomRepository customService;
	
	@ApiOperation(value = "Cadastra uma compra.")
	@PostMapping()
	public Compra create(@RequestBody Compra compra){
		return service.create(compra);
	}
	
	@ApiOperation(value = "Atualiza uma compra.")
	@PutMapping()
	public Compra update(@RequestBody Compra compra) {
		return service.update(compra);
	}
	
	@ApiOperation(value = "Busca uma compra por código.")
	@GetMapping(value = "/{codigo}")
	public Compra findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Busca uma compra por código.")
	@GetMapping(value = "/usuario/{codigo}")
	public List<Compra> findByUsuarioCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findByUsuarioCodigo(codigo);
	}
	
	@ApiOperation(value = "Retorna uma obeto com a quantidade compras efetuadas no periodo. 1 - datas passadas por parâmetro; 2 - ultimos 30 dias; 3 - ultimos 15 dias; Data formato : yyyy-dd-MM HH:mm:ss")
	@GetMapping("/relatorio/vendas_por_periodo/{periodo}/{dataInicio}/{dataFim}")
	public RelatorioCompraTicketMedio totalDeComprasPorPeriodo(
			@RequestParam(value = "periodo") int periodo,
			@RequestParam(value = "dataInicio", required = false) String dataInicio,
			@RequestParam(value = "dataFim", required = false) String dataFim){
		return customService.buscarPorPeriodo(periodo, dataInicio, dataFim);
	}
	
	@ApiOperation(value = "Retorna uma lista com todas as compras.")
	@GetMapping("")
	public List<Compra> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta uma compra por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
