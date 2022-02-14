package com.formacionjava.app.apirest.service;

import java.util.List;

import com.formacionjava.app.apirest.entity.Compra;

public interface CompraService {
	//GUARDAR EN ARRAYLIST LAS COMPRAR
	public List<Compra> findAll();
	
	//BUSCAR ID
	public Compra findById(Long codCliente);
	
	//BORRAR COMPRA
//	public void delete (Long codCliente);
	
	//GUARDAR COMPRA PARA ACTUALIZAR
//	public Compra save(Compra compra);
}
