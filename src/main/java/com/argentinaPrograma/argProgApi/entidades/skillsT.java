package com.argentinaPrograma.argProgApi.entidades;

import javax.persistence.*;

@Entity
@Table(name = "skills")
public class skillsT {

    @Id
    @Column(name = "idskill")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSkill;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "nivel")
    private Integer nivel;

    public Integer getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Integer idSkill) {
        this.idSkill = idSkill;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public skillsT() {
    }

    public skillsT(String titulo, Integer nivel) {
        this.titulo = titulo;
        this.nivel = nivel;
    }
}
