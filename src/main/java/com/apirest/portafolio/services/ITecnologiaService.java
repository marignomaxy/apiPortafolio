package com.apirest.portafolio.services;

import java.util.Optional;
import com.apirest.portafolio.entitys.Tecnologia;

public interface ITecnologiaService {

    Optional<Tecnologia> findByTituloTecnologia(String titulo_tecnologia);

    Optional<Tecnologia> findById(Integer id);
}
