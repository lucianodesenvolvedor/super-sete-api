package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

}
