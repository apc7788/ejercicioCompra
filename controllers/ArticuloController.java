package com.formacionjava.app.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.formacionjava.app.apirest.entity.Articulos;
import com.formacionjava.app.apirest.service.ArticuloService;


@RestController
@RequestMapping("/api")
public class ArticuloController {
	
	@Autowired
	private ArticuloService servicio;
	
	@GetMapping("/articulos")
	public List<Articulos> cliente() {
		return servicio.findAll();
		
	}
	

	//----------------------------------------------------------------------------------------------------------------------------
	
	
	//READ HECHO
	
	@GetMapping("/articulos/{id}")
	public ResponseEntity<?> articulosShow(@PathVariable Long id){
		Articulos articulo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			articulo = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (articulo == null) {
			response.put("Mensaje, ","El articulo ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Articulos>(articulo, HttpStatus.OK);	
	}
	
	

	//----------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	// DELETE ARTICULOS
	
	@DeleteMapping("/articulos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteArticuloMostrado(@PathVariable  Long id) {
		Articulos articuloBorrado= servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {	
			
			if (articuloBorrado == null) {
				response.put("Mensaje, ","El articulo ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			servicio.delete(id);
			
		}
			catch (DataAccessException e) {
			response.put("Mensaje", "Error al borrar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		response.put("Mensaje","El articulo ha sido borrado :D");
		response.put("cliente", articuloBorrado);
		 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	

	//----------------------------------------------------------------------------------------------------------------------------
	
	
	//ACTUALIZAR ARTICULOS
	
	
	@PutMapping("/articulos/{id}")
	public ResponseEntity<?> updateCliente(@RequestBody Articulos articulos, @PathVariable Long id) {
		
		Articulos articuloUpdated = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {
			articuloUpdated.setNombre(articulos.getNombre());
			articuloUpdated.setDescripcion(articulos.getDescripcion());
			articuloUpdated.setPrecioU(articulos.getPrecioU());
			articuloUpdated.setUnidades(articulos.getUnidades());
			articuloUpdated.setStockSeguridad(articulos.getStockSeguridad());
			
			servicio.save(articuloUpdated);
			
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El articulo ha sido actualizado :D");
		response.put("cliente", articuloUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	

	//----------------------------------------------------------------------------------------------------------------------------
	
	
	
	//INSERTAR ARTICULOS
	
	
	@PostMapping("/articulos")
	public ResponseEntity<?> saveCliente(@RequestBody Articulos articulos) {
		Articulos articuloNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			articuloNew = servicio.save(articulos);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar insert a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El articulo ha sido creado correctamente :D");
		response.put("Cliente", articuloNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	//INSERTAR IMAGENES
	
	@PostMapping("/articulos/uploads")
	public ResponseEntity<?> uploadImagen(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		
		Map<String, Object> response = new HashMap<>();
		Articulos articulo = servicio.findById(id);
		
		if(!archivo.isEmpty()) {
			//String nombreArchivo = archivo.getOriginalFilename();
			String nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" "," ");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("Mensaje", "Error al insertar imagen en la base de datos :(");
				response.put("Error", e.getMessage().concat("_").concat(e.getCause().getMessage())); 
			}
				
			String nombreFotoAnterior = articulo.getImagen();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0) {
				
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}	
			}
			
			articulo.setImagen(nombreArchivo);
			
			servicio.save(articulo);
			
			response.put("articulo", articulo);
			response.put("mensaje", "Has subido correctamente la imagen :D " + nombreArchivo);
		} else {
			response.put("archivo", "No se pudo encontrar");
		}
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	//DESCARGAR IMAGENES EN EL NAVEGADOR
	
	@GetMapping("/articulos/uploads/imgs/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException ("Error no se puede cargar la imagen: "+nombreImagen);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+recurso.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------

	
}

