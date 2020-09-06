package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByGondolaCodigo(Long codido);
	
	@Query("select produto from Produto produto where produto.destaque = :destaque")
	List<Produto> findByDestaque(@Param("destaque") boolean destaque);
	
	List<Produto> findByDescricaoContainingIgnoreCase(String descricao);
}
