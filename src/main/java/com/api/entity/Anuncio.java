package com.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "anuncio")
public class Anuncio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sequence_anuncio", sequenceName = "sequence_anuncio", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_anuncio")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "titulo", length = 250, nullable = false)
	private String titulo;
	@Column(name = "descricao", length = 250, nullable = false)
	private String descricao;
	@Column(name = "imagem", length = 250, nullable = false)
	private String imagem;
	@Column(name = "data_inicio")
	private Date dataInicio;
	@Column(name = "data_fim")
	private Date dataFim;
	@Column(name = "status")
	private boolean status;
}
