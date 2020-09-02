package com.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "compra_produto")
public class CompraProduto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sequence_compra_produto", sequenceName = "sequence_compra_produto", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_compra_produto")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "preco_unitario", nullable = false)
	private BigDecimal precoUnitario;
	@Column(name = "quantidade", nullable = false)
	private int quantidade;
	
	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")	
	private Produto produto;

}
