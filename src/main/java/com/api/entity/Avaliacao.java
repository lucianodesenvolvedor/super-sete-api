package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sequence_avaliacao", sequenceName = "sequence_avaliacao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_avaliacao")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "nota", nullable = false)
	private int nota;
	@Column(name = "descricao", length = 250)
	private String descricao;
	@Column(name = "status")
	private boolean status;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@PrePersist
	public void prePersist() {
		status = false;
	}

}
