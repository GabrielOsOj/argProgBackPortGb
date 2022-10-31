package com.argentinaPrograma.argProgApi.entidades;


import org.hibernate.annotations.GeneratorType;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proyectos")
public class proyectosT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "creada_por_id",updatable = false)
    private List<imagenesT> imagenes;

    public proyectosT() {
    }

    public proyectosT(Integer id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    public List<imagenesT> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<imagenesT> imagenes) {
        this.imagenes = imagenes;
    }
}
