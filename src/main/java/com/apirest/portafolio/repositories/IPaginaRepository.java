package com.apirest.portafolio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirest.portafolio.entitys.Pagina;

public interface IPaginaRepository extends JpaRepository<Pagina, Integer> {

    @Query("SELECT p FROM Pagina p WHERE p.titulo_pagina = :titulo")
    Optional<Pagina> findByTitulo(@Param("titulo") String titulo);

}
