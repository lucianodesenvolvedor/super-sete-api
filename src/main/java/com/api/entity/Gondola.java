package com.api.entity;

import java.io.Serializable;

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
@Table(name = "gondola")
public class Gondola implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sequence_gondola", sequenceName = "sequence_gondola", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_gondola")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "descricao", length = 250, nullable = false)
	private String gondola;
	@Column(name = "status")
	private boolean status;

}
