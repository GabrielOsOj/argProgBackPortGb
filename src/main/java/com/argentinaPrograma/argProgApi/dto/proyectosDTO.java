package com.argentinaPrograma.argProgApi.dto;

import java.util.List;

public class proyectosDTO {

    private Integer id;
    private String titulo;
    private String descripcion;
    private List<Integer> idImg;

    public proyectosDTO(Integer id, String titulo, String descripcion, List<Integer> idImg) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idImg = idImg;
    }

    public proyectosDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Integer> getIdImg() {
        return idImg;
    }

    public void setIdImg(List<Integer> idImg) {
        this.idImg = idImg;
    }
}
