package com.api.entity.model;

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
public class RelatorioCompraTicketMedio {

	private long quantidade;
	private BigDecimal valorTotal;
	private BigDecimal valorMedio;
}
