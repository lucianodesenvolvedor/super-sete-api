package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sequence_endereco", sequenceName = "sequence_endereco", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_endereco")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "estado", length = 25, nullable = false)
	private String estado;
	@Column(name = "cidade", length = 75, nullable = false)
	private String cidade;
	@Column(name = "bairro", length = 75, nullable = false)
	private String bairro;
	@Column(name = "cep", length = 15)
	private String cep;
	@Column(name = "endereco", length = 75, nullable = false)
	private String endereco;
	@Column(name = "numero", length = 10, nullable = false)
	private String numero;
	@Column(name = "complemento", length = 75, nullable = false)
	private String complemento;
	@Column(name = "status")
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
}
