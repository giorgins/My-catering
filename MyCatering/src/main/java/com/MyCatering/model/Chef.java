package com.MyCatering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Chef {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String nome;
	
	private String cognome;
	
	private String nazionalita;
	
	@OneToMany(mappedBy = "chef", cascade =CascadeType.ALL)
	private List<Buffet> buffets;
	
	public Chef() {
		this.buffets=new ArrayList<>();
	}

	public Chef(Long id, String nome, String cognome, String nazionalita, List<Buffet> buffets) {
	
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.buffets=new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Buffet> getBuffets() {
		return buffets;
	}

	public void setBuffets(List<Buffet> buffets) {
		this.buffets = buffets;
	}
 
}
