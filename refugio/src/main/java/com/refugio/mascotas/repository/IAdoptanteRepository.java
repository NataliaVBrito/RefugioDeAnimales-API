package com.refugio.mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.refugio.mascotas.entity.AdoptanteEntity;

@Repository
public interface IAdoptanteRepository extends JpaRepository<AdoptanteEntity, Integer> {

}
