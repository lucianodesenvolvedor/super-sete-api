package com.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.api.enums.PerfilEnum;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sequence_usuario", sequenceName = "sequence_usuario", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_usuario")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "nome", length = 45, nullable = false)
	private String nome;
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	@Column(name = "email", length = 75, nullable = false)
	private String email;
	@Column(name = "senha", length = 40, nullable = false)
	private String senha;
	@Column(name = "perfil", length = 25, nullable = false)
	@Enumerated(EnumType.STRING)
	private PerfilEnum perfil;
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;
	@Column(name = "data_ultimo_acesso", nullable = false)
	private Date dataUltimoAcesso;
	@Column(name = "status")
	private boolean status;
	
	@PrePersist
	public void prePersist() {
		final Date dataAtual = new Date();
		dataCadastro = dataAtual;
		dataUltimoAcesso = dataAtual;
		status = true;
	}

	@PreUpdate
	public void preUpdate() {
		final Date dataAtual = new Date();
		dataUltimoAcesso = dataAtual;
	}
}
