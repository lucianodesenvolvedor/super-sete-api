package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.CompraProduto;

@Repository
public interface CompraProdutoRepository extends JpaRepository<CompraProduto, Long> {

	List<CompraProduto> findByCompraCodigo(Long codigo);
}
