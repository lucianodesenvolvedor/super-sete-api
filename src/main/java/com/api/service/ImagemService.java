package com.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Imagem;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.ImagemRepository;

@Service
public class ImagemService {

	@Autowired
	ImagemRepository repository;
	
	public Imagem create(Imagem imagem) {
		return repository.save(imagem);
	}
	
	public Imagem update(Imagem imagem) {
		repository.findById(imagem.getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Imagem não encontrado com o código: " + imagem.getCodigo()));
		return repository.save(imagem);
	}
	
	public Imagem findById(long codigo) {		
		return repository.findById(codigo);
	}
	
	public Imagem findByAnuncio(Long codigo) {
		return repository.findByAnuncioCodigo(codigo);		
	}
	
	public Imagem findByProduto(Long codigo) {
		return repository.findByProdutoCodigo(codigo);		
	}
	
	public Imagem findByPromocao(Long codigo) {
		return repository.findByProdutoCodigo(codigo);		
	}
	
	public Imagem findByGondola(Long codigo) {
		return repository.findByGondolaCodigo(codigo);		
	}
	
	public boolean delete(Long codigo) {
		Imagem imagem = repository.findById(codigo).orElseThrow(
				() -> new ResourceNotFoundException("Imagem não encontrado com o código: " + codigo));
		boolean validate = false;
		if(imagem != null) {
			repository.delete(imagem);
			validate = true;
		}
		return validate;		
	}
}
