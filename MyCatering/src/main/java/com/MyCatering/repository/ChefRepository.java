package com.MyCatering.repository;

import org.springframework.data.repository.CrudRepository;

import com.MyCatering.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {

	boolean existsByNomeAndCognome(String nome, String cognome);

}
