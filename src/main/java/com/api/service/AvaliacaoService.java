package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Avaliacao;
import com.api.entity.Usuario;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.AvaliacaoRepository;
import com.api.repository.UsuarioRepository;

@Service
public class AvaliacaoService {

	@Autowired
	AvaliacaoRepository repository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public Avaliacao create(Avaliacao avaliacao) {
		Usuario usuario = usuarioRepository.findById(avaliacao.getUsuario().getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + avaliacao.getCodigo()));
		avaliacao.setUsuario(usuario);
		return repository.save(avaliacao);
	}

	public Avaliacao update(Avaliacao avaliacao) {
		repository.findById(avaliacao.getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + avaliacao.getCodigo()));
		return repository.save(avaliacao);
	}

	public Avaliacao findById(Long codigo) {
		Avaliacao avaliacao = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + codigo));
		return avaliacao;
	}

	public List<Avaliacao> findAll() {
		return repository.findAll();
	}

	public boolean delete(Long codigo) {
		Avaliacao avaliacao = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrado com o código: " + codigo));
		repository.delete(avaliacao);
		return true;
	}
}
