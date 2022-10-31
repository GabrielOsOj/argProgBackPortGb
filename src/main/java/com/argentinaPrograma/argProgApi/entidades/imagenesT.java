package com.argentinaPrograma.argProgApi.entidades;

import javax.persistence.*;

@Entity
@Table(name = "imagenesp")
public class imagenesT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombreimg")
    private  String nombreimg;

    @Column(name = "imgenbits")
    private byte[] imgenbits;

    @Column(name = "creada_por_id")
    private Integer creada_por_id;


    public imagenesT(String nombreimg, byte[] imgenbits, Integer creada_por_id) {
        this.nombreimg = nombreimg;
        this.imgenbits = imgenbits;
        this.creada_por_id = creada_por_id;
    }

    public imagenesT() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreimg() {
        return nombreimg;
    }

    public void setNombreimg(String nombreimg) {
        this.nombreimg = nombreimg;
    }

    public byte[] getImgenbits() {
        return imgenbits;
    }

    public void setImgenbits(byte[] imgenbits) {
        this.imgenbits = imgenbits;
    }

    public Integer getCreada_por_id() {
        return creada_por_id;
    }

    public void setCreada_por_id(Integer creada_por_id) {
        this.creada_por_id = creada_por_id;
    }
}
