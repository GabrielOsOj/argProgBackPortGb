package com.argentinaPrograma.argProgApi.seguridad.entidades;

import javax.persistence.*;

@Entity
@Table(name = "tokkensv")
public class tokkenT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tokken")
    private String tokken;

    @Column(name = "creado_por")
    private Integer creado_por;


    public tokkenT() {
    }

    public tokkenT(String tokken, Integer creado_por) {
        this.tokken = tokken;
        this.creado_por = creado_por;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTokken() {
        return tokken;
    }

    public void setTokken(String tokken) {
        this.tokken = tokken;
    }

    public Integer getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(Integer creado_por) {
        this.creado_por = creado_por;
    }
}

