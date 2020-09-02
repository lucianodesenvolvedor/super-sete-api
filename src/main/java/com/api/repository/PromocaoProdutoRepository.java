package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.api.entity.PromocaoProduto;

@Service
public interface PromocaoProdutoRepository extends JpaRepository<PromocaoProduto, Long> {

	List<PromocaoProduto> findByPromocaoCodigo(@Param("codigo") Long codigo);
}
