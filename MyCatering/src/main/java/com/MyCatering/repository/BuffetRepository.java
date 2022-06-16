package com.MyCatering.repository;

import org.springframework.data.repository.CrudRepository;

import com.MyCatering.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	boolean existsByNomeAndDescrizione(String nome, String descrizione);

}
