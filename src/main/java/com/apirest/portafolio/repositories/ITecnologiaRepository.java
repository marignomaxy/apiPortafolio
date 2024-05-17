package com.apirest.portafolio.repositories;

import com.apirest.portafolio.entitys.Tecnologia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITecnologiaRepository extends JpaRepository<Tecnologia, Integer> {

    Optional<Tecnologia> findByTituloTecnologia(String titulo_tecnologia);
}