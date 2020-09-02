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
@Table(name = "promocao_produto")
public class PromocaoProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sequence_promocao_produto", sequenceName = "sequence_promocao_produto", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_promocao_produto")
	@Column(name = "id")
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "promocao_id")
	private Promocao promocao;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

}
