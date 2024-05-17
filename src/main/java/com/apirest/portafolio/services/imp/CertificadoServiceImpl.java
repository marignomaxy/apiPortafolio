package com.apirest.portafolio.services.imp;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apirest.portafolio.entitys.Certificado;
import com.apirest.portafolio.repositories.ICertificadoRepository;
import com.apirest.portafolio.services.ICertificadoService;

@Service
public class CertificadoServiceImpl implements ICertificadoService {

    private ICertificadoRepository certificadoRepository;

    public CertificadoServiceImpl(ICertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    @Override
    public Certificado create(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

    @Override
    public Optional<Certificado> findById(Integer id) {
        return certificadoRepository.findById(id);
    }

    @Override
    public Optional<Certificado> findByTitulo(String titulo) {
        return certificadoRepository.findByNombre(titulo);
    }

    @Override
    public boolean update(Integer id, Certificado certificado) {
        Optional<Certificado> certificadoporId = certificadoRepository.findById(id);
        if (certificadoporId.isPresent()) {
            Certificado certificadoActual = certificadoporId.get();

            // Usa la reflexión para iterar sobre todos los campos
            for (Field field : Certificado.class.getDeclaredFields()) {
                field.setAccessible(true); // Necesario si los campos son privados
                try {
                    Object valorNuevo = field.get(certificado);
                    Object valorActual = field.get(certificadoActual);
                    if (valorNuevo != null && !Objects.equals(valorNuevo, valorActual)) {
                        System.out.println("El campo '" + field.getName() + "' se actualizó");
                        field.set(certificadoActual, valorNuevo);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            certificadoRepository.save(certificadoActual);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Certificado> certificado = certificadoRepository.findById(id);
        if (certificado.isPresent()) {
            certificadoRepository.delete(certificado.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Certificado> findAll() {
        return certificadoRepository.findAll();
    }

}
