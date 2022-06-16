package com.MyCatering.repository;

import org.springframework.data.repository.CrudRepository;

import com.MyCatering.model.User;


public interface UserRepository extends CrudRepository<User, Long> {

}