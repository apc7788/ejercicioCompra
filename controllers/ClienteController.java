package com.formacionjava.app.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.formacionjava.app.apirest.entity.Cliente;
import com.formacionjava.app.apirest.service.ClienteService;


@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteService servicio;
	
	@GetMapping("/clientes")
	public List<Cliente> cliente() {
		return servicio.findAll();
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------

	
	//READ CLIENTES
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> clienteShow(@PathVariable Long id){
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (cliente == null) {
			response.put("Mensaje, ","El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	// DELETE CLIENTES
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteClienteMostrado(@PathVariable  Long id) {
		Cliente clienteBorrado= servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {	
			
			if (clienteBorrado == null) {
				response.put("Mensaje, ","El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			servicio.delete(id);
			
		}
			catch (DataAccessException e) {
			response.put("Mensaje", "Error al borrar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		response.put("Mensaje","El cliente ha sido borrado :D");
		response.put("cliente", clienteBorrado);
		 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	//----------------------------------------------------------------------------------------------------------------------------
	
	
	//ACTUALIZAR CLIENTE
	
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		
		Cliente clienteUpdated = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteUpdated.setNombre(cliente.getNombre());
			clienteUpdated.setApellido(cliente.getApellido());
			clienteUpdated.setEmpresa(cliente.getEmpresa());
			clienteUpdated.setPuesto(cliente.getPuesto());
			clienteUpdated.setCp(cliente.getCp());
			clienteUpdated.setProvincia(cliente.getProvincia());
			clienteUpdated.setTelefono(cliente.getTelefono());
			clienteUpdated.setFechaNacimiento(cliente.getFechaNacimiento());
			
			servicio.save(clienteUpdated);
			
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El cliente ha sido actualizado :D");
		response.put("cliente", clienteUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	

	//----------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	//INSERTAR CLIENTE
	
	
	@PostMapping("/clientes")
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteNew = servicio.save(cliente);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar insert a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El cliente ha sido creado correctamente :D");
		response.put("Cliente", clienteNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	

	//----------------------------------------------------------------------------------------------------------------------------
	
}