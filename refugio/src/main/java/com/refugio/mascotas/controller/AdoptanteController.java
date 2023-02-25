package com.refugio.mascotas.controller;

import java.util.Arrays;
import java.util.Date;
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

import com.refugio.mascotas.entity.AdoptanteEntity;
import com.refugio.mascotas.entity.MascotaEntity;
import com.refugio.mascotas.repository.IAdoptanteRepository;
import com.refugio.mascotas.repository.IMascotaRepository;

@RestController
@RequestMapping("/refugio")
public class AdoptanteController {

	@Autowired
	private IAdoptanteRepository adoptanteRepository;

	@Autowired
	private IMascotaRepository mascotaRepository;

	@GetMapping("/adoptantes")
	public List<AdoptanteEntity> mostrarAdoptantes() {
		return adoptanteRepository.findAll();
	}

	@GetMapping("/adoptante/{idAdoptante}")
	public AdoptanteEntity mostrarPorId(@PathVariable int idAdoptante) {
		AdoptanteEntity adoptante = adoptanteRepository.findById(idAdoptante).get();

		if (!adoptanteRepository.existsById(idAdoptante)) {
			throw new RuntimeException("No existe la mascota " + idAdoptante);
		}

		return adoptante;
	}

	@PostMapping("/adoptante/crear")
	public void crearAdoptante(@RequestBody AdoptanteEntity adoptante) {
		adoptante.setId(0);
		adoptanteRepository.save(adoptante);
	}

	@PutMapping("/adoptante/editar")
	public void editarAdoptante(@RequestBody AdoptanteEntity adoptante) {
		adoptanteRepository.save(adoptante);
	}

	@PutMapping("/adoptante/editar/{idAdoptante}")
	public void editarAdoptantePorId(@RequestBody AdoptanteEntity adoptante, @PathVariable Integer idAdoptante) {
		AdoptanteEntity adoptanteE = adoptanteRepository.findById(idAdoptante).get();

		if (!adoptanteRepository.existsById(idAdoptante)) {
			throw new RuntimeException("No existe el adoptante " + idAdoptante);
		}

		adoptanteE.setNombre(adoptante.getNombre());
		adoptanteE.setDireccion(adoptante.getDireccion());

		adoptanteRepository.save(adoptanteE);
	}

	@DeleteMapping("/eliminarAdoptante/{idAdoptante}")
	public void borrarAdoptantePorId(@PathVariable Integer idAdoptante) {
		if (!adoptanteRepository.existsById(idAdoptante)) {
			throw new RuntimeException("No existe la mascota " + idAdoptante);
		}

		adoptanteRepository.deleteById(idAdoptante);
	}

	@PostMapping("/adoptar/{idAdoptante}/{idMascota}")
	public AdoptanteEntity adoptar(@PathVariable Integer idAdoptante, @PathVariable Integer idMascota) {
		MascotaEntity mascota = mascotaRepository.findById(idMascota).get();
		AdoptanteEntity adoptante = this.mostrarPorId(idAdoptante);

		if (mascota == null) {
			throw new RuntimeException("No existe la mascota " + idMascota);
		}

		if (adoptante == null) {
			throw new RuntimeException("No existe el adoptante " + idAdoptante);
		}

		if (mascota.getAdoptante() == null) {
			mascota.setAdoptante(adoptante);
			mascota.setFechaAdopcion(new Date());
		} else {
			throw new RuntimeException("La mascota " + idMascota + " fue adoptada por el adoptante " + idAdoptante);
		}
		mascotaRepository.save(mascota);

		if (adoptante.getIdMascota() != null) {
			adoptante.getIdMascota().add(mascota);

		} else {
			adoptante.setIdMascota(Arrays.asList(mascota));
		}

		adoptanteRepository.save(adoptante);

		return adoptante;
	}

}
