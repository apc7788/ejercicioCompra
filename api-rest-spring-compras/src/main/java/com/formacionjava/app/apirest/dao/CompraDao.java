package com.formacionjava.app.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionjava.app.apirest.entity.Compra;

@Repository
public interface CompraDao extends CrudRepository<Compra, Long> {

}