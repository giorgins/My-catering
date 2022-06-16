package com.MyCatering.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;

	private String descrizione;
	
	private String origine;
	
	@ManyToOne
	private Piatto piatto;
	




	public  Ingrediente() {
			
	}
	
	

	public Ingrediente(String nome, String descrizione, String origine) {
		
		this.nome = nome;
		this.descrizione = descrizione;
		this.origine = origine;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}


	
	public Piatto getPiatto() {
		return piatto;
	}



	public void setPiatto(Piatto piatto) {
		this.piatto = piatto;
	}
	
	
}
