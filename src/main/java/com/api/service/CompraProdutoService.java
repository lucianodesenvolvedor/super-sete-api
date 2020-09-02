package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Compra;
import com.api.entity.CompraProduto;
import com.api.entity.Produto;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.CompraProdutoRepository;
import com.api.repository.CompraRepository;
import com.api.repository.ProdutoRepository;

@Service
public class CompraProdutoService {

	@Autowired
	CompraProdutoRepository repository;

	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;

	public CompraProduto create(CompraProduto compraProduto) {
		Compra compra = compraRepository.findById(compraProduto.getCompra().getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Compra não encontrada com o código: " + compraProduto.getCodigo()));
		
		Produto produto = produtoRepository.findById(compraProduto.getCompra().getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Produto não encontrado com o código: " + compraProduto.getCodigo()));
		
		compraProduto.setCompra(compra);
		compraProduto.setProduto(produto);
		
		return repository.save(compraProduto);
	}

	public CompraProduto update(CompraProduto compraProduto) {
		compraRepository.findById(compraProduto.getCompra().getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Compra não encontrada com o código: " + compraProduto.getCodigo()));
		
		produtoRepository.findById(compraProduto.getCompra().getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Produto não encontrado com o código: " + compraProduto.getCodigo()));

		return repository.save(compraProduto);
	}

	public CompraProduto findById(Long codigo) {
		CompraProduto compraProduto = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Lista de compra não encontrada com o código: " + codigo));
		return compraProduto;
	}

	public List<CompraProduto> findByCompraCodigo(Long codigo) {
		compraRepository.findById(codigo).orElseThrow(
				() -> new ResourceNotFoundException("Compra não encontrada com o código: " + codigo));
		return repository.findByCompraCodigo(codigo);
	}
	
	public List<CompraProduto> findAll() {
		return repository.findAll();
	}

	public boolean delete(Long codigo) {
		CompraProduto compraProduto = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Lista de compra não encontrada com o código: " + codigo));
		repository.delete(compraProduto);
		return true;
	}
}
