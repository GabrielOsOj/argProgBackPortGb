package com.argentinaPrograma.argProgApi.seguridad.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class usuariosT {

    @Id
    @Column(name = "iduser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iduser;

    @Column(name = "email")
    private String email;

    @Column(name = "contraseña")
    private String contraseña;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "creado_por")
    private List<tokkenT> usTokken;

    public List<tokkenT> getUsTokken() {
        return usTokken;
    }

    public void setUsTokken(List<tokkenT> usTokken) {
        this.usTokken = usTokken;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
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
