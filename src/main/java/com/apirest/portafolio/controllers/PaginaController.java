package com.apirest.portafolio.controllers;

import java.nio.file.*;
import java.util.*;

import org.apache.commons.io.FilenameUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.apirest.portafolio.entitys.Pagina;
import com.apirest.portafolio.entitys.Tecnologia;
import com.apirest.portafolio.exceptions.ResourceNotFoundException;
import com.apirest.portafolio.services.IPaginaService;
import com.apirest.portafolio.services.ITecnologiaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/paginas")
public class PaginaController {

    private IPaginaService paginaService;
    private ITecnologiaService tecnologiaService;

    public PaginaController(IPaginaService paginaService, ITecnologiaService tecnologiaService) {
        this.paginaService = paginaService;
        this.tecnologiaService = tecnologiaService;
    }

    @GetMapping
    public ResponseEntity<List<Pagina>> getPagina(HttpServletRequest request) {
        String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        List<Pagina> paginas = paginaService.findAll();
        String urlImg = serverUrl + "/img/";
        paginas.forEach(pagina -> {
            pagina.setUrl_foto(urlImg + pagina.getUrl_foto());
        });

        return ResponseEntity.ok(paginas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaginaById(@PathVariable("id") Integer id, HttpServletRequest request) {
        Optional<Pagina> paginaPorId = paginaService.findById(id);
        try {
            Pagina pagina = paginaPorId
                    .orElseThrow(() -> new NoSuchElementException("No existe una página con el ID proporcionado"));
            String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String urlImg = serverUrl + "/img/";
            pagina.setUrl_foto(urlImg + pagina.getUrl_foto());
            return ResponseEntity.ok(pagina);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al obtener la página: " + e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> guardarPagina(
            @RequestPart("pagina") String paginaJson,
            @RequestParam("file") MultipartFile image) {
        try {
            ObjectMapper objetoMapeador = new ObjectMapper();
            Pagina pagina = objetoMapeador.readValue(paginaJson, Pagina.class);

            if (!image.isEmpty()) {
                Path directorioImagen = Paths.get("src//main//resources//static//img");
                String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();
                try {
                    byte[] bytes = image.getBytes();
                    String tituloSinEspacios = "pagina_" + pagina.getTitulo_pagina().replace(" ", "");
                    String extension = FilenameUtils.getExtension(image.getOriginalFilename());
                    String nuevoNombreArchivo = tituloSinEspacios + "." + extension;
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nuevoNombreArchivo);
                    Files.write(rutaCompleta, bytes);
                    pagina.setUrl_foto(nuevoNombreArchivo);

                } catch (Exception e) {
                    System.err.println("Error al guardar la imagen: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error al guardar la imagen: " + e.getMessage());
                }
            }

            // Convertir IDs de tecnologias en objetos Tecnologia
            Set<Tecnologia> tecnologiasAsociadas = new HashSet<>();
            for (Tecnologia tecnologia : pagina.getTecnologias()) {
                Optional<Tecnologia> tecnologiaOptional = tecnologiaService.findById(tecnologia.getId());
                if (tecnologiaOptional.isPresent()) {
                    tecnologiasAsociadas.add(tecnologiaOptional.get());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("La tecnología con ID " + tecnologia.getId() + " no fue encontrada.");
                }
            }
            pagina.setTecnologias(tecnologiasAsociadas);

            Pagina savedPagina = paginaService.create(pagina);
            return ResponseEntity.ok("Se guardó correctamente la página " + savedPagina);

        } catch (DataAccessException e) {
            System.err.println("Error al guardar la página: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la página: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putPagina(@PathVariable("id") Integer id, @RequestBody Pagina pagina) {
        try {
            boolean seActualizo = paginaService.update(id, pagina);
            if (seActualizo) {
                return ResponseEntity.ok("Se actualizo correctamente la pagina con id" + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la página con ID: " + id);
            }
        } catch (DataAccessException e) {
            System.err.println("Error al actualizar la página: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar la página: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePagina(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        try {
            boolean seElimino = paginaService.delete(id);
            if (seElimino) {
                return ResponseEntity.ok("Se eliminó la página con ID: " + id + " correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la página con ID: " + id);
            }
        } catch (DataAccessException e) {
            System.err.println("Error al eliminar la página: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar la página: " + e.getMessage());
        }
    }

}
