package com.refugio.mascotas.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mascota")
public class MascotaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mascota")
	private Integer idMascota;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "edad")
	private int edad;

	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	@Column(name = "fecha_admision")
	private Date fechaAdmision;
	
	@JsonIgnore
	@JoinColumn(name = "id_adoptante", nullable = true)
	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private AdoptanteEntity adoptante;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_adopcion")
	private Date fechaAdopcion;

	public MascotaEntity() {
	}

	public MascotaEntity(Integer idMascota, String nombre, String tipo, int edad, Date fechaAdmision,
			AdoptanteEntity adoptante, Date fechaAdopcion) {
		this.idMascota = idMascota;
		this.nombre = nombre;
		this.tipo = tipo;
		this.edad = edad;
		this.fechaAdmision = fechaAdmision;
		this.adoptante = adoptante;
		this.fechaAdopcion = fechaAdopcion;
	}

	public Integer getIdMascota() {
		return idMascota;
	}

	public void setId(Integer idMascota) {
		this.idMascota = idMascota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFechaAdmision() {
		return fechaAdmision;
	}

	public void setFechaAdmision(Date fechaAdmision) {
		this.fechaAdmision = fechaAdmision;
	}

	public AdoptanteEntity getAdoptante() {
		return adoptante;
	}

	public void setAdoptante(AdoptanteEntity adoptante) {
		this.adoptante = adoptante;
	}

	public Date getFechaAdopcion() {
		return fechaAdopcion;
	}

	public void setFechaAdopcion(Date fechaAdopcion) {
		this.fechaAdopcion = fechaAdopcion;
	}

	@Override
	public String toString() {
		return "MascotaEntity [idMascota=" + idMascota + ", nombre=" + nombre + ", tipo=" + tipo + ", edad=" + edad
				+ ", fechaAdmision=" + fechaAdmision + ", adoptante=" + adoptante + ", fechaAdopcion=" + fechaAdopcion
				+ "]";
	}

}
