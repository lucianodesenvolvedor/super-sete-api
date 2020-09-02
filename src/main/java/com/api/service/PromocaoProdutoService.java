package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.PromocaoProduto;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.ProdutoRepository;
import com.api.repository.PromocaoProdutoRepository;
import com.api.repository.PromocaoRepository;

@Service
public class PromocaoProdutoService {

	@Autowired
	PromocaoProdutoRepository repository;

	@Autowired
	PromocaoRepository promocaoRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	public PromocaoProduto create(PromocaoProduto promocaoProduto) {
		promocaoRepository.findById(promocaoProduto.getPromocao().getCodigo())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Promoção não encontrada com o código: " + promocaoProduto.getPromocao().getCodigo()));

		produtoRepository.findById(promocaoProduto.getPromocao().getCodigo())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Produto não encontrado com o código: " + promocaoProduto.getPromocao().getCodigo()));

		return repository.save(promocaoProduto);
	}

	public PromocaoProduto update(PromocaoProduto promocaoProduto) {
		promocaoRepository.findById(promocaoProduto.getPromocao().getCodigo())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Promoção não encontrada com o código: " + promocaoProduto.getPromocao().getCodigo()));

		produtoRepository.findById(promocaoProduto.getPromocao().getCodigo())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Produto não encontrado com o código: " + promocaoProduto.getPromocao().getCodigo()));
		return repository.save(promocaoProduto);
	}

	public PromocaoProduto findById(Long codigo) {
		PromocaoProduto promocaoProduto = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + codigo));
		return promocaoProduto;
	}

	public List<PromocaoProduto> findByPromocaoCodigo(Long codigo) {
		return repository.findByPromocaoCodigo(codigo);
	}

	public List<PromocaoProduto> findAll() {
		return repository.findAll();
	}

	public boolean delete(Long codigo) {
		PromocaoProduto promocaoProduto = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado com o código: " + codigo));
		repository.delete(promocaoProduto);
		return true;
	}
}
