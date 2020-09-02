package com.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Promocao;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.PromocaoRepository;

@Service
public class PromocaoService {

	@Autowired
	PromocaoRepository repository;

	public Promocao create(Promocao promocao) {
		return repository.save(promocao);
	}

	public Promocao update(Promocao promocao) {
		repository.findById(promocao.getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Promoção não encontrada com o código: " + promocao.getCodigo()));
		return repository.save(promocao);
	}

	public Promocao findById(Long codigo) {
		Promocao promocao = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Promoção não encontrada com o código: " + codigo));
		return promocao;
	}
	
	public List<Promocao> findByDataInicioAndDataFim(Date dataInicio, Date dataFim) {
		return repository.findByDataInicioAndDataFim(dataInicio, dataFim);
	}

	public List<Promocao> findAll() {
		return repository.findAll();
	}

	public boolean delete(Long codigo) {
		Promocao promocao = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Promoção não encontrada com o código: " + codigo));
		repository.delete(promocao);
		return true;
	}
}
