package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Gondola;
import com.api.entity.Produto;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.GondolaRepository;
import com.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

	@Autowired
	GondolaRepository gondolaRepository;

	public Produto create(Produto produto) {
		Gondola gondola = gondolaRepository.findById(produto.getGondola().getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Gondola não encontrada com o código: " + produto.getCodigo()));
		produto.setGondola(gondola);
		return repository.save(produto);
	}

	public Produto update(Produto produto) {
		repository.findById(produto.getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Produto não encontrado com o código: " + produto.getCodigo()));
		return repository.save(produto);
	}

	public Produto findById(Long codigo) {
		Produto produto = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + codigo));
		return produto;
	}

	public List<Produto> findByDestaque() {
		return repository.findByDestaque(true);
	}

	public List<Produto> findByDescricao(String descricao) {
		return repository.findByDescricaoContainingIgnoreCase(descricao);
	}

	public List<Produto> findByGondolaCodigo(Long codigo) {
		return repository.findByGondolaCodigo(codigo);
	}

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public boolean delete(Long codigo) {
		Produto endereco = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o código: " + codigo));
		repository.delete(endereco);
		return true;
	}
}
