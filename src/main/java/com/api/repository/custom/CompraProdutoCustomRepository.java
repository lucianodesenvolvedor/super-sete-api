package com.api.repository.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.entity.model.RelatorioCompraProdutoPeriodo;
import com.api.utils.DataUtils;

@Repository
public class CompraProdutoCustomRepository {

	private final EntityManager em;
	
	@Autowired
	DataUtils dataService;

	public CompraProdutoCustomRepository(EntityManager em) {
		this.em = em;
	}

	public List<RelatorioCompraProdutoPeriodo> buscarPorPeriodo(int periodo, String dataInicio, String dataFim) {
		
		List<RelatorioCompraProdutoPeriodo> lista = new ArrayList<RelatorioCompraProdutoPeriodo>();

		/*
		 * 	1 - datas passadas por parâmetro;
		 * 	2 - ultimos 30 dias;
		 * 	3 - ultimos 15 dias;
		 */
		Date dataPeriodoFim = null;
		Date dataPeriodoInicio = null;
		if (periodo == 1){
			dataPeriodoFim = dataService.converterStringParaData(dataFim);
			dataPeriodoInicio = dataService.converterStringParaData(dataInicio);
		} else if(periodo == 2) {
			dataPeriodoFim = dataService.periodo(0);
			dataPeriodoInicio = dataService.periodo(2);
		} else if(periodo == 3) {
			dataPeriodoFim = dataService.periodo(0);
			dataPeriodoInicio = dataService.periodo(3);
		}

		String consulta = "select new com.api.entity.model.RelatorioCompraProdutoPeriodo( "
				+ " p.descricao as descricao, sum(cp.quantidade) as quantidade, cp.precoUnitario, sum(cp.quantidade * cp.precoUnitario) as precoTotal  "
				+ " ) from CompraProduto cp join cp.compra c join cp.produto p "
				+ " where c.dataCompra between :dataInicial and :dataFinal " 
				+ " group by p.descricao, cp.precoUnitario "
				+ " order by quantidade desc ";
		TypedQuery<RelatorioCompraProdutoPeriodo> query = em.createQuery(consulta, RelatorioCompraProdutoPeriodo.class);			
			query.setParameter("dataInicial", dataPeriodoInicio);
			query.setParameter("dataFinal", dataPeriodoFim);
			
		lista = query.getResultList();
		return lista;
	}
}
