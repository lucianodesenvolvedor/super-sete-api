package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Endereco;
import com.api.entity.Usuario;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.EnderecoRepository;
import com.api.repository.UsuarioRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository repository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public Endereco create(Endereco endereco) {
		Usuario usuario = usuarioRepository.findById(endereco.getUsuario().getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + endereco.getCodigo()));
		endereco.setUsuario(usuario);
		return repository.save(endereco);
	}

	public Endereco update(Endereco endereco) {
		repository.findById(endereco.getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + endereco.getCodigo()));
		return repository.save(endereco);
	}

	public Endereco findById(Long codigo) {
		Endereco endereco = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + codigo));
		return endereco;
	}

	public List<Endereco> findAll() {
		return repository.findAll();
	}

	public boolean delete(Long codigo) {
		Endereco endereco = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado com o código: " + codigo));
		repository.delete(endereco);
		return true;
	}
}
