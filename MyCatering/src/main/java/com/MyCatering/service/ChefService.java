package com.MyCatering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyCatering.model.Chef;

import com.MyCatering.repository.ChefRepository;



@Service
public class ChefService {
	@Autowired
	private ChefRepository chefRepository;
	
	@Transactional
	public void save(Chef chef) {
		 chefRepository.save(chef);
	}


	public List<Chef> findAll() {
		List<Chef> elencoChef = new ArrayList<Chef>();
		for(Chef b : chefRepository.findAll()) {
			elencoChef.add(b);
		}
		return elencoChef;
	
	}
	//vedere nel repo
	public boolean aldreadyExist(Chef buffet) {
		return this.chefRepository.existsByNomeAndCognome(buffet.getNome(), buffet.getCognome());
	}

	
	@Transactional
	public List<Chef> tutti() {
		
		return  (List<Chef>) chefRepository.findAll();
	}

	public Chef getById(Long id) {
	
		return chefRepository.findById(id).orElse(null);
	}

	public void inserisci(Chef chef) {
		this.chefRepository.save(chef);
	}

	public void elimina(Long id) {
		this.chefRepository.deleteById(id);
	}

	



}
