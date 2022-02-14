package com.formacionjava.app.apirest.controllers;

import java.util.HashMap;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.dao.DataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionjava.app.apirest.entity.Compra;
import com.formacionjava.app.apirest.service.CompraService;

@RestController
@RequestMapping("/api")
public class CompraController {
	
	@Autowired
	private CompraService compraService;
	
	@GetMapping("/compras")
	public List<Compra> compra() {
		return compraService.findAll();
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------

	
	//READ COMPRAS
	
	@GetMapping("/compras/{id}")
	public ResponseEntity<?> compraShow(@PathVariable Long id){
		Compra compra = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			compra = compraService.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (compra == null) {
			response.put("Mensaje, ","La compra con el cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Compra>(compra, HttpStatus.OK);
		
	}
}
