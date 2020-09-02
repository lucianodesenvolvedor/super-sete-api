package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Usuario;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario create(Usuario usuario) {
		return repository.save(usuario);
	}

	public Usuario update(Usuario usuario) {
		repository.findById(usuario.getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + usuario.getCodigo()));
		return repository.save(usuario);
	}

	public Usuario findById(Long codigo) {
		Usuario usuario = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + codigo));
		return usuario;
	}

	public Usuario login(String telefone, String senha) {
		Usuario usuario = repository.findBytelefoneAndSenha(telefone, senha);
		if (usuario == null) {
			return new Usuario();
		}
		return usuario;
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public boolean validateEmail(String email) {
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
			return false;
		}
		return true;
	}

	public boolean delete(Long codigo) {
		Usuario usuario = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o código: " + codigo));
		repository.delete(usuario);
		return true;
	}

}
