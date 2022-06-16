package com.MyCatering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.MyCatering.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {

	boolean existsByNomeAndDescrizione(String nome, String descrizione);

	@Query(nativeQuery=true, value="SELECT *  FROM piatto ORDER BY random() LIMIT 3")
	public List<Piatto> getRandomPiatto();
}
