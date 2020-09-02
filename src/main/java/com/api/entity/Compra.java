package com.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "compra")
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sequence_compra", sequenceName = "sequence_compra", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_compra")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "data_compra", nullable = false)
	private Date dataCompra;
	@Column(name = "valor_compra", nullable = false)
	private BigDecimal valorCompra;
	@Column(name = "status")
	private boolean status;	
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@PrePersist
	public void prePersist() {
		final Date dataAtual = new Date();
		dataCompra = dataAtual;
		valorCompra = new BigDecimal("0.00");
	}

}
