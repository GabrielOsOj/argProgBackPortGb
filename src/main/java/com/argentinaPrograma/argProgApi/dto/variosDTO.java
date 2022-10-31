package com.argentinaPrograma.argProgApi.dto;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public class variosDTO {

    private Integer id;
    private String titulo;
    private String nombre;
    private String descripcion;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public variosDTO(Integer id, String titulo, String nombre, String descripcion, String url) {
        this.id = id;
        this.titulo = titulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url=url;

    }

    public variosDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
