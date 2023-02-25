package com.refugio.mascotas.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "adoptante")
public class AdoptanteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_adoptante")
	private Integer idAdoptante;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	@Column(name = "dni")
	private int DNI;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "telefono")
	private int telefono;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adoptante")
	private List<MascotaEntity> mascotas;

	public AdoptanteEntity() {
	}

	public AdoptanteEntity(Integer idAdoptante, String nombre, String apellido, int dNI, String direccion, int telefono,
			List<MascotaEntity> idMascota) {
		this.idAdoptante = idAdoptante;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mascotas = idMascota;
	}

	public Integer getId() {
		return idAdoptante;
	}

	public void setId(Integer id) {
		this.idAdoptante = id;
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

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public List<MascotaEntity> getIdMascota() {
		return mascotas;
	}

	public void setIdMascota(List<MascotaEntity> idMascota) {
		this.mascotas = idMascota;
	}

	@Override
	public String toString() {
		return "AdoptanteEntity [idAdoptante=" + idAdoptante + ", nombre=" + nombre + ", apellido=" + apellido + ", DNI=" + DNI
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", idMascota=" + mascotas + "]";
	}

}
