package com.formacionjava.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionjava.app.apirest.dao.ArticuloDao;
import com.formacionjava.app.apirest.entity.Articulos;

@Service
public class ArticuloServiceImpl implements ArticuloService {
	
	@Autowired
	private ArticuloDao articuloDao;
	
	//LEER CLIENTES

	@Override
	@Transactional(readOnly = true)
	public List<Articulos> findAll() {
		return (List<Articulos>) articuloDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Articulos findById(Long id) {
		return  articuloDao.findById(id).orElse(null);
	}
	
	//BORRAR CLIENTES

	@Override
	public void delete(Long id) {
		articuloDao.deleteById(id);	
	}
	
	//MODIFICAR CLIENTES

	@Override
	public Articulos save(Articulos articulos) {
		return articuloDao.save(articulos);
	}
	
	
}
