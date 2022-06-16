package com.MyCatering.repository;

import org.springframework.data.repository.CrudRepository;

import com.MyCatering.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

	boolean existsByNomeAndDescrizione(String nome, String descrizione);

}
