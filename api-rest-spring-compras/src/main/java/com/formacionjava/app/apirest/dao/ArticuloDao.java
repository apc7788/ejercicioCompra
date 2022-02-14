package com.formacionjava.app.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionjava.app.apirest.entity.Articulos;

@Repository
public interface ArticuloDao extends CrudRepository<Articulos, Long> {

}