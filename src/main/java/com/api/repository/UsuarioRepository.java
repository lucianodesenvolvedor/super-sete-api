package com.api.repository;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.entity.Usuario;

@Repository
@NamedQueries({
	@NamedQuery(name = "UsuarioRepository.findByEmail", query = "select usuario from Usuario usuario where usuario.email = :email; ")
	
	})
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	//Usuario findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);
	Usuario findBytelefoneAndSenha(@Param("telefone") String telefone, @Param("senha") String senha);
	Usuario findByEmail(@Param("email") String email);
}
