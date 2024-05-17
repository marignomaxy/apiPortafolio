package com.apirest.portafolio.entitys;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "certificados")
public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_certificado")
    private Integer id;

    @Column(name = "titulo_certificado")
    private String titulo_certificado;

    @Column(name = "esDeGrado")
    private boolean esDeGrado;

    @Column(name = "institucion")
    private String institucion;

    @Column(name = "descripcion_certificado")
    private String descripcion_certificado;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "url_certificado")
    private String url_certificado;

    @Column(name = "url_foto_certificado")
    private String url_foto_certificado;

}
