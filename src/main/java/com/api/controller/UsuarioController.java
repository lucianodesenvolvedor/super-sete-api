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
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Usuario;
import com.api.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Controle das funções do usuário")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService service;
	
	@ApiOperation(value = "Cadastra um usuário.")
	@PostMapping()
	public Usuario create(@RequestBody Usuario usuario){
		return service.create(usuario);
	}
	
	@ApiOperation(value = "Atualiza um usuário.")
	@PutMapping()
	public Usuario update(@RequestBody Usuario person) {
		return service.update(person);
	}
	
	@ApiOperation(value = "Busca um usuário por código.")
	@GetMapping(value = "/{codigo}")
	public Usuario findByCodigo(@PathVariable(value = "codigo") Long codigo) {
		return service.findById(codigo);
	}
	
	@ApiOperation(value = "Efetua o login do usuário por telefone e senha.")
	@GetMapping("/login/{telefone}/{senha}")
	public Usuario login(@PathVariable(value = "telefone") String telefone, @PathVariable(value = "senha") String senha) {
		return service.login(telefone, senha);
	}
	
	@ApiOperation(value = "Verifica se um e-Mail já existe.")
	@GetMapping("/email/{email}")
	public boolean validateEmail(@PathVariable(value = "email") String email) {
		return service.validateEmail(email);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os usuários.")
	@GetMapping("")
	public List<Usuario> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Deleta um usuário por código.")
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<?> delete(@PathVariable(value = "codigo") Long codigo) {
		 service.delete(codigo);
		 return ResponseEntity.ok().build();
	}
}
