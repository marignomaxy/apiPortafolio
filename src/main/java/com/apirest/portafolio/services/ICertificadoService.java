package com.apirest.portafolio.services;

import java.util.List;
import java.util.Optional;

import com.apirest.portafolio.entitys.Certificado;

public interface ICertificadoService {

    Certificado create(Certificado pagina);

    Optional<Certificado> findByTitulo(String titulo);

    Optional<Certificado> findById(Integer id);

    boolean update(Integer id, Certificado certificado);

    boolean delete(Integer id);

    List<Certificado> findAll();

}
