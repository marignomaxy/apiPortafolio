package com.apirest.portafolio.services;

import java.util.List;
import java.util.Optional;

import com.apirest.portafolio.entitys.Pagina;

public interface IPaginaService {

    Pagina create(Pagina pagina);

    Optional<Pagina> findByTitulo(String titulo);

    Optional<Pagina> findById(Integer id);

    boolean update(Integer id, Pagina pagina);

    boolean delete(Integer id);

    List<Pagina> findAll();

}
