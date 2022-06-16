package com.MyCatering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyCatering.model.Buffet;
import com.MyCatering.repository.BuffetRepository;



@Service
public class BuffetService {
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Transactional
	public void save(Buffet buffet) {
		 buffetRepository.save(buffet);
	}

	public Buffet getById(Long id) {
		
		return buffetRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public List<Buffet> tutti(){
		return (List<Buffet>) buffetRepository.findAll();
	}
	
	public List<Buffet> findAll() {
		List<Buffet > buffets = new ArrayList<Buffet>();
		for(Buffet b : buffetRepository.findAll()) {
			buffets.add(b);
		}
		return buffets;
	
	}
	//vedere nel repo
	public boolean aldreadyExist(Buffet buffet) {
		return this.buffetRepository.existsByNomeAndDescrizione(buffet.getNome(), buffet.getDescrizione());
	}
	
	public void inserisci(Buffet buffet) {
		this.buffetRepository.save(buffet);
	}
	public void elimina(Long id) {
		this.buffetRepository.deleteById(id);
		
	}



}
