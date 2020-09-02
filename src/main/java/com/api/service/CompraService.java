package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Compra;
import com.api.entity.Usuario;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.CompraRepository;
import com.api.repository.UsuarioRepository;

@Service
public class CompraService {

	@Autowired
	CompraRepository repository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public Compra create(Compra compra) {
		Usuario usuario = usuarioRepository.findById(compra.getUsuario().getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + compra.getCodigo()));
		compra.setUsuario(usuario);
		return repository.save(compra);
	}

	public Compra update(Compra compra) {
		repository.findById(compra.getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Compra não encontrado com o código: " + compra.getCodigo()));
		return repository.save(compra);
	}

	public Compra findById(Long codigo) {
		Compra compra = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Compra não encontrado com o código: " + codigo));
		return compra;
	}

	public List<Compra> findByUsuarioCodigo(Long codigo) {
		Usuario usuario = usuarioRepository.findById(codigo).orElseThrow(
				() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + codigo));
		return repository.findByUsuarioCodigo(usuario.getCodigo());
	}
	
	public List<Compra> findAll() {
		return repository.findAll();
	}

	public boolean delete(Long codigo) {
		Compra compra = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Compra não deletada com o código: " + codigo));
		repository.delete(compra);
		return true;
	}
}
