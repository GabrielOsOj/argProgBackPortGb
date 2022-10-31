package com.argentinaPrograma.argProgApi.dto;

public class skillsDTO {

    Integer idSkill;
    String titulo;
    Integer nivel;

    public skillsDTO(Integer id, String titulo, Integer nivel) {
        this.idSkill = id;
        this.titulo = titulo;
        this.nivel = nivel;
    }

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
}
