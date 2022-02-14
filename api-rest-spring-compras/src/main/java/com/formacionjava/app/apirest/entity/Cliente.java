package com.formacionjava.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String apellido;
	
	@Column(nullable=false)
	private String empresa;
	
	@Column(nullable=false)
	private String puesto;
	
	@Column(nullable=false)
	private int cp;
	
	@Column(nullable=false)
	private String provincia;
	
	@Column(nullable=false)
	private int telefono;
	
	@Column(nullable=false)
	private String fechaNacimiento;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
//	@Column(name="create_at")
//	@Temporal(TemporalType.DATE)
//	private Date createdAt;
	
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="region_id")
//	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
//	private Region region;
	
//	@PrePersist
//	public void prePersist() {
//		createdAt= new Date();
//	}	
	
	
}
