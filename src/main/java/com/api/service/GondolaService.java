package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Gondola;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.GondolaRepository;

@Service
public class GondolaService {

	@Autowired
	GondolaRepository repository;


	public Gondola create(Gondola gondola) {
		return repository.save(gondola);
	}

	public Gondola update(Gondola gondola) {
		repository.findById(gondola.getCodigo()).orElseThrow(
				() -> new ResourceNotFoundException("Gondola não encontrada com o código: " + gondola.getCodigo()));
		return repository.save(gondola);
	}

	public Gondola findById(Long codigo) {
		Gondola gondola = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Gondola não encontrada com o código: " + codigo));
		return gondola;
	}

	public List<Gondola> findAll() {
		return repository.findAll();
	}

	public boolean delete(Long codigo) {
		Gondola gondola = repository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException("Gondola não encontrada com o código: " + codigo));
		repository.delete(gondola);
		return true;
	}
}
