package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {

	Imagem findById(long codigo);

	Imagem findByAnuncioCodigo(Long codigo);

	Imagem findByProdutoCodigo(Long codigo);

	Imagem findByPromocaoCodigo(Long codigo);
	
	Imagem findByGondolaCodigo(Long codigo);
}
