package com.formacionjava.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="compras")
public class Compra implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long cod_cliente;
	
	
	@Column(nullable=false)
	private long cod_articulo;

//	@OneToMany(fetch=FetchType.LAZY)
//	@JoinColumn(name="cod_cliente")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Cliente cliente;

	
//	@OneToMany(fetch=FetchType.LAZY)
//	@JoinColumn(name="cod_articulo")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Articulos articulo;
	
	@Column(unique=true)
	private String fecha;
	
	@Column(nullable=false)
	private int unidades;
	
	
	public long getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(long cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public long getCod_articulo() {
		return cod_articulo;
	}

	public void setCod_articulo(long cod_articulo) {
		this.cod_articulo = cod_articulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	
	
	
}
