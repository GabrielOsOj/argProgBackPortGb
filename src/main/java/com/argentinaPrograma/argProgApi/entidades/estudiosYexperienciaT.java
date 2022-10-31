package com.argentinaPrograma.argProgApi.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "estudiosyexperiencia")
public class estudiosYexperienciaT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name = "titulo",nullable = false)
    private String titulo;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    @Column(name = "url",nullable = false)
    private byte[] url;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipo")
    private tiposT tipo;

    public estudiosYexperienciaT() {
    }

    public estudiosYexperienciaT(Integer id, String titulo, String descripcion, byte[] url, tiposT tipo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.tipo = tipo;
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

    public byte[] getUrl() {
        return url;
    }

    public void setUrl(byte[] url) {
        this.url = url;
    }

    public tiposT getTipo() {
        return tipo;
    }

    public void setTipo(tiposT tipo) {
        this.tipo = tipo;
    }
}
