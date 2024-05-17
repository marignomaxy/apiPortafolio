package com.apirest.portafolio.entitys;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tecnologias")
public class Tecnologia {

    public Tecnologia(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tecnologia")
    private Integer id;

    @Column(name = "titulo_tecnologia")
    private String tituloTecnologia;

    @Column(name = "url_foto_tecnologia")
    private String url_foto_tecnologia;
}