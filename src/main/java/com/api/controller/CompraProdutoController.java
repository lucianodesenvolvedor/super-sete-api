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

import com.api.entity.CompraProduto;
import com.api.entity.model.RelatorioCompraProdutoPeriodo;
import com.api.repository.custom.CompraProdutoCustomRepository;
import com.api.service.CompraProdutoService;
import com.api.utils.DataUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle dos compras de produtos")
@RestController
@RequestMapping("/api/compra_produto")
public class CompraProdutoController {

	@Autowired
	CompraProdutoService service;
	
	@Autowired
	CompraProdutoCustomRepository customService;
	
	@Autowired
	DataUtils dataService;

	@ApiOperation(value = "Cadastra um produto na lista.")
	@PostMapping()
	public CompraProduto create(@RequestBody CompraProduto compraProduto) {
		return service.create(compraProduto);
	}

	@ApiOperation(value = "Atualiza um item da lista de produtos.")
	@PutMapping()
	public CompraProduto update(@RequestBody CompraProduto compraProduto) {
		return service.update(compraProduto);
	}

	@ApiOperation(value = "Busca um produto da lista por código.")
	@GetMapping(value = "/{codigo}")
	public CompraProduto findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}

	@ApiOperation(value = "Retorna uma lista com todos os produtos.")
	@GetMapping("")
	public List<CompraProduto> findAll() {
		return service.findAll();
	}
	@ApiOperation(value = "Retorna uma lista com a quantidade de cada produto vendido no período. 1 - datas passadas por parâmetro; 2 - ultimos 30 dias; 3 - ultimos 15 dias; Data formato : yyyy-dd-MM HH:mm:ss")
	@GetMapping("/relatorio/vendas_por_periodo/{periodo}/{dataInicio}/{dataFim}")
	public List<RelatorioCompraProdutoPeriodo> totalDeVendasPorPeriodo(
			@RequestParam(value = "periodo") int periodo,
			@RequestParam(value = "dataInicio", required = false) String dataInicio,
			@RequestParam(value = "dataFim", required = false) String dataFim){
		return customService.buscarPorPeriodo(periodo, dataInicio, dataFim);
	}

	@ApiOperation(value = "Deleta um lista de produtos por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		service.delete(codigo);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Deleta todos o produtos de uma compra_produto por código do compra_produto.")
	@DeleteMapping("/compra_produto/{codigo}")
	public boolean deleteListaCompraProduto(@PathVariable(value = "codigo") long codigo) {
		List<CompraProduto> lista = service.findByCompraCodigo(codigo);
		for (CompraProduto produto : lista) {
			service.delete(produto.getCodigo());
		}
		return true;
	}
}
