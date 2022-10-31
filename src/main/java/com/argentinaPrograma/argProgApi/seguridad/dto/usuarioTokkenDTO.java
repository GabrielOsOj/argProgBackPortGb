package com.argentinaPrograma.argProgApi.seguridad.dto;


public class usuarioTokkenDTO {

    private Integer id;
    private String Tokken;

    public usuarioTokkenDTO() {
    }

    public usuarioTokkenDTO(Integer id, String tokken) {
        this.id = id;
        Tokken = tokken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTokken() {
        return Tokken;
    }

    public void setTokken(String tokken) {
        Tokken = tokken;
    }
}
