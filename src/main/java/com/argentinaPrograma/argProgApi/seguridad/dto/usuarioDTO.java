package com.argentinaPrograma.argProgApi.seguridad.dto;

public class usuarioDTO {

    private String email;

    private String contraseña;

    public usuarioDTO() {
    }

    public usuarioDTO(String email, String contraseña) {
        this.email = email;
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
