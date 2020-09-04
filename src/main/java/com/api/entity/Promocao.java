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
@Table(name = "promocao")
public class Promocao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sequence_promocao", sequenceName = "sequence_promocao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_promocao")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "descricao", length = 250, nullable = false)
	private String descricao;
	@Column(name = "data_inicio")
	private Date dataInicio;
	@Column(name = "data_fim")
	private Date dataFim;
	@Column(name = "status")
	private boolean status;
}
