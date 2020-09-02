package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Anuncio;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.AnuncioRepository;
import com.api.repository.UsuarioRepository;

@Service
public class AnuncioService {
	
	@Autowired
	AnuncioRepository repository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public Anuncio create(Anuncio anuncio) {
		return repository.save(anuncio);
	}

	public Anuncio update(Anuncio anuncio) {
		repository.findById(anuncio.getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Anuncio não encontrado com o código: " + anuncio.getCodigo()));
		return repository.save(anuncio);
	}

	public Anuncio findById(Long codigo) {
		Anuncio endereco = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Anuncio não encontrado com o código: " + codigo));
		return endereco;
	}

	public List<Anuncio> findAll() {
		return repository.findAll();
	}

	public boolean delete(Long codigo) {
		Anuncio endereco = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Anuncio não encontrado com o código: " + codigo));
		repository.delete(endereco);
		return true;
	}
}
