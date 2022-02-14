package com.formacionjava.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionjava.app.apirest.dao.ClienteDao;
import com.formacionjava.app.apirest.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteDao clienteDao;
	
	//LEER CLIENTES

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return  clienteDao.findById(id).orElse(null);
	}
	
	//BORRAR CLIENTES

	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);	
	}
	
	//MODIFICAR CLIENTES

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}
	
	
}
