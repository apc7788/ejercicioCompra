package com.formacionjava.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionjava.app.apirest.dao.CompraDao;
import com.formacionjava.app.apirest.entity.Compra;

@Service
public class CompraServiceImpl implements CompraService {

		
		@Autowired
		private CompraDao compraDao;

		//LEER COMPRA

		@Override
		@Transactional(readOnly = true)
		public List<Compra> findAll() {
			return (List<Compra>) compraDao.findAll();
		}

		@Override
		@Transactional(readOnly = true)
		public Compra findById(Long codCliente) {
			return  compraDao.findById(codCliente).orElse(null);
		}
		
		
//		@Override
//		public void delete(Long codCliente) {
//			compraDao.deleteById(codCliente);
//		}
		
	
//
//		@Override
//		public Compra save(Compra compra) {
//			return compraDao.save(compra);
//		}
		
}
