package com.refugio.mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.refugio.mascotas.entity.MascotaEntity;

@Repository
public interface IMascotaRepository extends JpaRepository<MascotaEntity, Integer> {

}
