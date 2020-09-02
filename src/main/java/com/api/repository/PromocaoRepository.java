package com.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.entity.Promocao;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long> {

	List<Promocao> findByDataInicioAndDataFim(@Param("dataInicio")  Date dataInicio, @Param("dataFim") Date dataFim);
}
