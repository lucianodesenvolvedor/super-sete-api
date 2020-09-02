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
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sequence_produto", sequenceName = "sequence_produto", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_produto")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "descricao", length = 250, nullable = false)
	private String descricao;
	@Column(name = "imagem", length = 200, nullable = false)
	private String imagem;
	@Column(name = "preco_de_custo", nullable = false)
	private BigDecimal precoDecusto;
	@Column(name = "preco_de_venda", nullable = false)
	private BigDecimal precoDeVenda;
	@Column(name = "preco_promocional", nullable = false)
	private BigDecimal precoPromocional;
	@Column(name = "quantidade", nullable = false)
	private int quantidade;
	@Column(name = "quantidade_minima", nullable = false)
	private int quantidadeMinima;
	@Column(name = "destaque")
	private boolean destaque;
	@Column(name = "oferta")
	private boolean oferta;
	@Column(name = "status")
	private boolean status;
		
	@ManyToOne
	@JoinColumn(name = "gondola_id")
	private Gondola gondola;
}
