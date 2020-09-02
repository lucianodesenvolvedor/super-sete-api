package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	List<Compra> findByUsuarioCodigo(Long codigo);
}
