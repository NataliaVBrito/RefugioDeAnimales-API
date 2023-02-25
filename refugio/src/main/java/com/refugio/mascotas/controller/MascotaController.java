package com.refugio.mascotas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refugio.mascotas.entity.MascotaEntity;
import com.refugio.mascotas.repository.IMascotaRepository;

@RestController
@RequestMapping("/refugio")
public class MascotaController {

	@Autowired
	private IMascotaRepository mascotaRepository;

	@GetMapping("/mascotas")
	public List<MascotaEntity> mostrarMascotas() {
		return mascotaRepository.findAll();
	}

	@GetMapping("/mascota/{idMascota}")
	public MascotaEntity mostrarPorId(@PathVariable Integer idMascota) {
		MascotaEntity mascota = mascotaRepository.findById(idMascota).get();
		
		if (!mascotaRepository.existsById(idMascota)) {
			throw new RuntimeException("No existe la mascota " + idMascota);
		}

		return mascota;
	}

	@PostMapping("/mascota/crear")
	public void crearMascota(@RequestBody MascotaEntity mascota) {
		mascota.setId(0);
		mascotaRepository.save(mascota);
	}

	@PutMapping("/mascota/editar")
	public void editarMascota(@RequestBody MascotaEntity mascota) {
		mascotaRepository.save(mascota);
	}

	@PutMapping("/mascota/editar/{idMascota}")
	public void editarMascotaId(@RequestBody MascotaEntity mascota, @PathVariable Integer idMascota) {
		MascotaEntity mascotaE = mascotaRepository.findById(idMascota).get();

		if (!mascotaRepository.existsById(idMascota)) {
			throw new RuntimeException("No existe la mascota " + idMascota);
		}

		mascotaE.setNombre(mascota.getNombre());
		mascotaE.setEdad(mascota.getEdad());

		mascotaRepository.saveAndFlush(mascotaE);
	}

	@DeleteMapping("/borrarMascota/{idMascota}")
	public void borrarMascota(@PathVariable Integer idMascota) {
		if (!mascotaRepository.existsById(idMascota)) {
			throw new RuntimeException("No existe la mascota " + idMascota);
		}

		mascotaRepository.deleteById(idMascota);
	}

}
