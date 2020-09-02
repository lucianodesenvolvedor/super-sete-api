package com.api.entity.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RelatorioCompraProdutoPeriodo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private long quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;

}
