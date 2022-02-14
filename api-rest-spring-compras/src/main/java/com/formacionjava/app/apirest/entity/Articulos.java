package com.formacionjava.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="articulos")
public class Articulos implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long cod_articulo;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String descripcion;
	
	@Column(nullable=false)
	private int precioU;
	
	@Column(nullable=false)
	private int unidades;
	
	@Column(nullable=false)
	private int stockSeguridad;
	
	private String imagen;

	public long getCodArticulo() {
		return cod_articulo;
	}


	public void setCodArticulo(long cod_articulo) {
		this.cod_articulo = cod_articulo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getPrecioU() {
		return precioU;
	}


	public void setPrecioU(int precioU) {
		this.precioU = precioU;
	}


	public int getUnidades() {
		return unidades;
	}


	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}


	public int getStockSeguridad() {
		return stockSeguridad;
	}


	public void setStockSeguridad(int stockSeguridad) {
		this.stockSeguridad = stockSeguridad;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
		
}

