package com.argentinaPrograma.argProgApi.entidades;

import javax.persistence.*;

@Entity
@Table(name = "varios")
public class variosT {

    @Id
    @Column(name = "idvarios")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "url")
    private byte[] url;

    public variosT(Integer id, String nombre, String titulo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public variosT() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
