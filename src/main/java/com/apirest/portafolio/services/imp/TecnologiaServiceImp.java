package com.apirest.portafolio.services.imp;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.apirest.portafolio.entitys.Tecnologia;
import com.apirest.portafolio.repositories.ITecnologiaRepository;
import com.apirest.portafolio.services.ITecnologiaService;

@Service
public class TecnologiaServiceImp implements ITecnologiaService {

    private ITecnologiaRepository tecnologiaRepository;

    public TecnologiaServiceImp(ITecnologiaRepository tecnologiaRepository) {
        this.tecnologiaRepository = tecnologiaRepository;
    }

    @Override
    public Optional<Tecnologia> findByTituloTecnologia(String titulo_tecnologia) {
        return tecnologiaRepository.findByTituloTecnologia(titulo_tecnologia);
    }

    @Override
    public Optional<Tecnologia> findById(Integer id) {
        return tecnologiaRepository.findById(id);
    }

}
