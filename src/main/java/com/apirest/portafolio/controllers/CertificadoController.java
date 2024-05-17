package com.apirest.portafolio.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apirest.portafolio.entitys.Certificado;
import com.apirest.portafolio.exceptions.ResourceNotFoundException;
import com.apirest.portafolio.services.ICertificadoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/certificados")
public class CertificadoController {

    private ICertificadoService certificadoService;

    public CertificadoController(ICertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    @GetMapping
    public ResponseEntity<List<Certificado>> getPagina(HttpServletRequest request) {
        String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        List<Certificado> certificados = certificadoService.findAll();
        String urlImg = serverUrl + "/img/";
        certificados.forEach(certificado -> {
            certificado.setUrl_foto_certificado(urlImg + certificado.getUrl_foto_certificado());
        });

        return ResponseEntity.ok(certificados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaginaById(@PathVariable("id") Integer id, HttpServletRequest request) {
        Optional<Certificado> certificadoPorId = certificadoService.findById(id);
        try {
            Certificado certificado = certificadoPorId
                    .orElseThrow(() -> new NoSuchElementException("No existe un certificado con el ID proporcionado"));
            String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String urlImg = serverUrl + "/img/";
            certificado.setUrl_foto_certificado(urlImg + certificado.getUrl_foto_certificado());
            return ResponseEntity.ok(certificado);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al obtener el certificado: " + e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> guardarPagina(
            @RequestPart("certificado") String certificadoJson,
            @RequestParam("file") MultipartFile image) {
        try {
            System.out.println(certificadoJson);
            ObjectMapper objetoMapeador = new ObjectMapper();
            Certificado certificado = objetoMapeador.readValue(certificadoJson, Certificado.class);
            System.out.println("certificado mapeado" + certificado);

            if (!image.isEmpty()) {
                Path directorioImagen = Paths.get("src//main//resources//static//img");
                String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();
                try {
                    byte[] bytes = image.getBytes();
                    String tituloSinEspacios = "certificado_" + certificado.getTitulo_certificado().replace(" ", "");
                    String extension = FilenameUtils.getExtension(image.getOriginalFilename());
                    String nuevoNombreArchivo = tituloSinEspacios + "." + extension;
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nuevoNombreArchivo);
                    Files.write(rutaCompleta, bytes);
                    certificado.setUrl_foto_certificado(nuevoNombreArchivo);

                } catch (Exception e) {
                    System.err.println("Error al guardar la imagen certificado: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error al guardar la imagen certificado: " + e.getMessage());
                }
            }
            Certificado savedCertificado = certificadoService.create(certificado);
            return ResponseEntity.ok("Se guardo correctamente el certificado" + savedCertificado);

        } catch (DataAccessException e) {
            System.err.println("Error al guardar el certificado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la página: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCertificado(@PathVariable("id") Integer id, @RequestBody Certificado certificado) {
        try {
            boolean seActualizo = certificadoService.update(id, certificado);
            if (seActualizo) {
                return ResponseEntity.ok("Se actualizo correctamente el certificado con id" + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el certificado con ID: " + id);
            }
        } catch (DataAccessException e) {
            System.err.println("Error al actualizar el certificado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al actualizar el certificado: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePagina(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        try {
            boolean seElimino = certificadoService.delete(id);
            if (seElimino) {
                return ResponseEntity.ok("Se eliminó el certificado con ID: " + id + " correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el certificado con ID: " + id);
            }
        } catch (DataAccessException e) {
            System.err.println("Error al eliminar el certificado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar el certificado: " + e.getMessage());
        }
    }

}
