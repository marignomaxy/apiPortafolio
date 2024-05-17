package com.apirest.portafolio.entitys;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paginas")

public class Pagina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagina")
    private Integer id;

    @Column(name = "titulo_pagina")
    private String titulo_pagina;

    @Column(name = "descripcion_pagina")
    private String descripcion_pagina;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pagina_tecnologia", joinColumns = @JoinColumn(name = "id_pagina"), inverseJoinColumns = @JoinColumn(name = "id_tecnologia"))
    private Set<Tecnologia> tecnologias;

    @Column(name = "destacado")
    private boolean destacado;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "url_repositorio")
    private String url_repositorio;

    @Column(name = "url_pagina")
    private String url_pagina;

    @Column(name = "url_foto")
    private String url_foto;
}
