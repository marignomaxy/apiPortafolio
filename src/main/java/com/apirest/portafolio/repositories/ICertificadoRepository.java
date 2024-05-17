package com.apirest.portafolio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirest.portafolio.entitys.Certificado;

public interface ICertificadoRepository extends JpaRepository<Certificado, Integer> {

    @Query("SELECT p FROM Certificado p WHERE p.titulo_certificado = :titulo")
    Optional<Certificado> findByNombre(@Param("titulo") String titulo);

}
