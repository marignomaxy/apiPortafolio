package com.apirest.portafolio.services.imp;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apirest.portafolio.entitys.Pagina;
import com.apirest.portafolio.repositories.IPaginaRepository;
import com.apirest.portafolio.services.IPaginaService;

@Service
public class PaginaServiceImpl implements IPaginaService {

    private IPaginaRepository paginaRepository;

    public PaginaServiceImpl(IPaginaRepository paginaRepository) {
        this.paginaRepository = paginaRepository;
    }

    @Override
    public Pagina create(Pagina pagina) {
        return paginaRepository.save(pagina);
    }

    @Override
    public Optional<Pagina> findById(Integer id) {
        return paginaRepository.findById(id);
    }

    @Override
    public Optional<Pagina> findByTitulo(String titulo) {
        return paginaRepository.findByTitulo(titulo);
    }

    @Override
    public boolean update(Integer id, Pagina pagina) {
        Optional<Pagina> paginaPorId = paginaRepository.findById(id);
        if (paginaPorId.isPresent()) {
            Pagina paginaActual = paginaPorId.get();

            // Usa la reflexión para iterar sobre todos los campos
            for (Field field : Pagina.class.getDeclaredFields()) {
                field.setAccessible(true); // Necesario si los campos son privados
                try {
                    Object valorNuevo = field.get(pagina);
                    Object valorActual = field.get(paginaActual);
                    if (valorNuevo != null && !Objects.equals(valorNuevo, valorActual)) {
                        System.out.println("El campo '" + field.getName() + "' se actualizó");
                        field.set(paginaActual, valorNuevo);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            paginaRepository.save(paginaActual);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Pagina> pagina = paginaRepository.findById(id);
        if (pagina.isPresent()) {
            paginaRepository.delete(pagina.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Pagina> findAll() {
        return paginaRepository.findAll();
    }

}
