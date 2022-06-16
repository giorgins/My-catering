package com.MyCatering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.MyCatering.model.Ingrediente;

import com.MyCatering.repository.IngredienteRepository;



@Service
public class IngredienteService {
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	
	@Transactional
	public void save(Ingrediente ingrediente) {
		 ingredienteRepository.save(ingrediente);
	}


	
	public List<Ingrediente> findAll() {
		List<Ingrediente > ingredienti = new ArrayList<Ingrediente>();
		for(Ingrediente b : ingredienteRepository.findAll()) {
			ingredienti.add(b);
		}
		return ingredienti;
	
	}
	public boolean aldreadyExist(Ingrediente ingrediente) {
		return this.ingredienteRepository.existsByNomeAndDescrizione(ingrediente.getNome(), ingrediente.getDescrizione());
	}



	public Ingrediente getById(Long id) {
		
		return ingredienteRepository.findById(id).orElse(null) ;
	}
	@Transactional
	public List<Ingrediente> tutti() {
	
		return (List<Ingrediente>) ingredienteRepository.findAll();
	}

	public void elimina(Long id) {
		this.ingredienteRepository.deleteById(id);
	}

	public void inserisci(Ingrediente ingrediente) {
		this.ingredienteRepository.save(ingrediente);
		
	}



}
