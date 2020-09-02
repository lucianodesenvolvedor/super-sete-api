package com.api.repository.custom;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.entity.model.RelatorioCompraTicketMedio;
import com.api.utils.DataUtils;

@Repository
public class CompraCustomRepository {

	private final EntityManager em;

	@Autowired
	DataUtils dataService;

	public CompraCustomRepository(EntityManager em) {
		this.em = em;
	}

	public RelatorioCompraTicketMedio buscarPorPeriodo(int periodo, String dataInicio, String dataFim) {

		RelatorioCompraTicketMedio ticket = new RelatorioCompraTicketMedio();

		/*
		 * 1 - datas passadas por parâmetro; 2 - ultimos 30 dias; 3 - ultimos 15 dias;
		 */
		Date dataPeriodoFim = null;
		Date dataPeriodoInicio = null;
		if (periodo == 1) {
			dataPeriodoFim = dataService.converterStringParaData(dataFim);
			dataPeriodoInicio = dataService.converterStringParaData(dataInicio);
		} else if (periodo == 2) {
			dataPeriodoFim = dataService.periodo(0);
			dataPeriodoInicio = dataService.periodo(2);
		} else if (periodo == 3) {
			dataPeriodoFim = dataService.periodo(0);
			dataPeriodoInicio = dataService.periodo(3);
		}

		String querys = " select new com.api.entity.model.RelatorioCompraTicketMedio( "
				+ " count(compra.codigo) as quantidade, sum(compra.valorCompra) as valorTotal, ( sum(compra.valorCompra) / count(compra.codigo) ) as valorMedio  "
				+ " ) from Compra compra  where compra.dataCompra between :dataInicial and :dataFinal ";
		TypedQuery<RelatorioCompraTicketMedio> query = em.createQuery(querys, RelatorioCompraTicketMedio.class);
		query.setParameter("dataInicial", dataPeriodoInicio);
		query.setParameter("dataFinal", dataPeriodoFim);

		ticket = query.getSingleResult();

		return ticket;
	}
}
