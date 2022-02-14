package com.formacionjava.app.apirest.service;

import java.util.List;


import com.formacionjava.app.apirest.entity.Cliente;

public interface ClienteService {
	
	//GUARDAR EN ARRAYLIST EL CLIENTE
	public List<Cliente> findAll();
	
	//BUSCAR ID
	public Cliente findById(Long id);
	
	//BORRAR CLIENTE
	public void delete (Long id);
	
	//GUARDAR CLIENTE PARA ACTUALIZAR
	public Cliente save(Cliente cliente);

}
