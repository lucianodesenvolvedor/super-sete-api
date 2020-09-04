package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "imagem")
public class Imagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sequence_imagem", sequenceName = "sequence_imagem", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_imagem")
	@Column(name = "id")
	private Long codigo;
	@Column(name = "nome")
	private String nome;	
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	@Column(name = "imagem")
	private byte[] imagem;
	
	@OneToOne
	@JoinColumn(name = "anuncio_id")
	private Anuncio anuncio;
	
	@OneToOne
	@JoinColumn(name = "gondola_id")
	private Gondola gondola;
	
	@OneToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@OneToOne
	@JoinColumn(name = "promocao_id")
	private Promocao promocao;
}
