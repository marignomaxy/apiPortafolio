package com.apirest.portafolio.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.portafolio.entitys.Tecnologia;

import jakarta.annotation.PostConstruct;

@Service
public class ITecnologiaImp {
        @Autowired
        private ITecnologiaRepository tecnologiaRepository;

        @PostConstruct
        public void init() {
                if (tecnologiaRepository.count() == 0) {
                        Tecnologia tecnologia1 = new Tecnologia();
                        tecnologia1.setTituloTecnologia("JavaScript");
                        tecnologia1.setUrl_foto_tecnologia(
                                        "https://upload.wikimedia.org/wikipedia/commons/6/6a/JavaScript-logo.png");

                        Tecnologia tecnologia2 = new Tecnologia();
                        tecnologia2.setTituloTecnologia("Java");
                        tecnologia2.setUrl_foto_tecnologia("https://cdn.worldvectorlogo.com/logos/java.svg");

                        Tecnologia tecnologia3 = new Tecnologia();
                        tecnologia3.setTituloTecnologia("HTML");
                        tecnologia3.setUrl_foto_tecnologia(
                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/HTML5_logo_and_wordmark.svg/2048px-HTML5_logo_and_wordmark.svg.png");

                        Tecnologia tecnologia4 = new Tecnologia();
                        tecnologia4.setTituloTecnologia("CSS");
                        tecnologia4.setUrl_foto_tecnologia(
                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/CSS3_logo_and_wordmark.svg/1452px-CSS3_logo_and_wordmark.svg.png");

                        Tecnologia tecnologia5 = new Tecnologia();
                        tecnologia5.setTituloTecnologia("Angular");
                        tecnologia5.setUrl_foto_tecnologia(
                                        "https://miro.medium.com/v2/resize:fit:1400/1*Klh1l7wkoG6PDPb9A5oCHQ.png");

                        Tecnologia tecnologia6 = new Tecnologia();
                        tecnologia6.setTituloTecnologia("React Js");
                        tecnologia6.setUrl_foto_tecnologia(
                                        "https://cdn1.iconfinder.com/data/icons/programing-development-8/24/react_logo-512.png");

                        Tecnologia tecnologia7 = new Tecnologia();
                        tecnologia7.setTituloTecnologia("Next js");
                        tecnologia7.setUrl_foto_tecnologia("https://cdn.worldvectorlogo.com/logos/next-js.svg");

                        Tecnologia tecnologia8 = new Tecnologia();
                        tecnologia8.setTituloTecnologia("Sass");
                        tecnologia8.setUrl_foto_tecnologia(
                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Sass_Logo_Color.svg/1280px-Sass_Logo_Color.svg.png");

                        Tecnologia tecnologia9 = new Tecnologia();
                        tecnologia9.setTituloTecnologia("Bootstrap");
                        tecnologia9.setUrl_foto_tecnologia(
                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Bootstrap_logo.svg/1200px-Bootstrap_logo.svg.png");

                        Tecnologia tecnologia10 = new Tecnologia();
                        tecnologia10.setTituloTecnologia("Tailwind css");
                        tecnologia10.setUrl_foto_tecnologia(
                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Tailwind_CSS_Logo.svg/1280px-Tailwind_CSS_Logo.svg.png");

                        Tecnologia tecnologia11 = new Tecnologia();
                        tecnologia11.setTituloTecnologia("Node Js + Express");
                        tecnologia11.setUrl_foto_tecnologia(
                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Node.js_logo.svg/2560px-Node.js_logo.svg.png");

                        Tecnologia tecnologia12 = new Tecnologia();
                        tecnologia12.setTituloTecnologia("Spring");
                        tecnologia12.setUrl_foto_tecnologia("https://cdn.worldvectorlogo.com/logos/spring-3.svg");

                        Tecnologia tecnologia13 = new Tecnologia();
                        tecnologia13.setTituloTecnologia("Postgre Sql");
                        tecnologia13.setUrl_foto_tecnologia(
                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/1200px-Postgresql_elephant.svg.png");

                        Tecnologia tecnologia14 = new Tecnologia();
                        tecnologia14.setTituloTecnologia("Mongo DB");
                        tecnologia14.setUrl_foto_tecnologia(
                                        "https://cdn.icon-icons.com/icons2/2415/PNG/512/mongodb_original_wordmark_logo_icon_146425.png");

                        Tecnologia tecnologia15 = new Tecnologia();
                        tecnologia15.setTituloTecnologia("Git");
                        tecnologia15.setUrl_foto_tecnologia(
                                        "https://git-scm.com/images/logos/downloads/Git-Icon-1788C.png");

                        Tecnologia tecnologia16 = new Tecnologia();
                        tecnologia16.setTituloTecnologia("AWS");
                        tecnologia16.setUrl_foto_tecnologia(
                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Amazon_Web_Services_Logo.svg/2560px-Amazon_Web_Services_Logo.svg.png");

                        Tecnologia tecnologia17 = new Tecnologia();
                        tecnologia17.setTituloTecnologia("Vercel");
                        tecnologia17
                                        .setUrl_foto_tecnologia(
                                                        "https://static-00.iconduck.com/assets.00/vercel-icon-512x449-3422jidz.png");

                        Tecnologia tecnologia18 = new Tecnologia();
                        tecnologia18.setTituloTecnologia("Railway");
                        tecnologia18.setUrl_foto_tecnologia("https://railway.app/brand/logotype-dark.svg");

                        tecnologiaRepository.save(tecnologia1);
                        tecnologiaRepository.save(tecnologia2);
                        tecnologiaRepository.save(tecnologia3);
                        tecnologiaRepository.save(tecnologia4);
                        tecnologiaRepository.save(tecnologia5);
                        tecnologiaRepository.save(tecnologia6);
                        tecnologiaRepository.save(tecnologia7);
                        tecnologiaRepository.save(tecnologia8);
                        tecnologiaRepository.save(tecnologia9);
                        tecnologiaRepository.save(tecnologia10);
                        tecnologiaRepository.save(tecnologia11);
                        tecnologiaRepository.save(tecnologia12);
                        tecnologiaRepository.save(tecnologia13);
                        tecnologiaRepository.save(tecnologia14);
                        tecnologiaRepository.save(tecnologia15);
                        tecnologiaRepository.save(tecnologia16);
                        tecnologiaRepository.save(tecnologia17);
                        tecnologiaRepository.save(tecnologia18);
                }
        }

}
