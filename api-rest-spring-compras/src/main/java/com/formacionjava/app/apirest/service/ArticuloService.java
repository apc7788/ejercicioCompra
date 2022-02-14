package com.formacionjava.app.apirest.service;

import java.util.List;


import com.formacionjava.app.apirest.entity.Articulos;

public interface ArticuloService {
	
	//GUARDAR EN ARRAYLIST EL ARTICULO
	public List<Articulos> findAll();
	
	//BUSCAR ID
	public Articulos findById(Long id);
	
	//BORAR ARTICULO
	public void delete (Long id);
	
	//GUARDAR ARTICULO PARA ACTUALIZAR
	public Articulos save(Articulos cliente);

}
