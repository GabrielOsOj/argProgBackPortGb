package com.argentinaPrograma.argProgApi.seguridad.servicios;

import com.argentinaPrograma.argProgApi.seguridad.dto.usuarioDTO;
import com.argentinaPrograma.argProgApi.seguridad.dto.usuarioTokkenDTO;

import com.argentinaPrograma.argProgApi.seguridad.entidades.tokkenT;
import com.argentinaPrograma.argProgApi.seguridad.entidades.usuariosT;

import com.argentinaPrograma.argProgApi.seguridad.repository.tokkenTrepository;
import com.argentinaPrograma.argProgApi.seguridad.repository.usuariosTrepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class usuariosService {
    @Autowired
    private usuariosTrepository usuarioTrepo;


    @Autowired
    private tokkenService tokkenSv = new tokkenService(){};



    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 32, 64);

    public usuarioTokkenDTO iniciar(usuarioDTO user) throws Exception {
        usuariosT usuario = this.filtrar(user.getEmail());

        if (this.argon2.verify(usuario.getContraseña(), user.getContraseña())) {

            if (!usuario.getUsTokken().isEmpty()) {
                this.tokkenSv.tokkenDestroy(usuario.getUsTokken().get(0).getId());
            }

            String tokken = this.tokkenSv.TokkenGen(usuario.getIduser(), 30);

            return new usuarioTokkenDTO(usuario.getIduser(), tokken);
        } else {
            throw new Exception("credenciales no validas!");
        }

    }


    public Boolean cerrarSesion(usuarioTokkenDTO usuario) {
        try {
            this.tokkenSv.tokkenDestroy(this.usuarioTrepo.findById(usuario.getId()).get().getUsTokken().get(0).getId());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("NO SE PUEDE CERRAR SESION!");
            return false;
        }

    }


    public Boolean validarTokken(usuarioTokkenDTO usuario) {

        try {
            Optional<usuariosT> usuarioLocal = this.usuarioTrepo.findById(usuario.getId());
            if (usuarioLocal.isPresent()) {
                String tokkenLocal = usuarioLocal.get().getUsTokken().get(0).getTokken();
                return this.tokkenSv.tokkenChecker(usuario.getTokken(),tokkenLocal);
            } else {
                System.out.println("NO EXISTE EL USUARIO EN LA BDD!");
                return false;
            }


        } catch (Exception e) {
            System.out.println(e);
            System.out.println("TOKKEN INVALIDO!");
            return false;
        }

    }


    private usuariosT filtrar(String email) throws Exception {

        List<usuariosT> usuarios = this.usuarioTrepo.findAll();

        for (Integer i = 0; i < usuarios.size(); i++) {
            usuariosT usuario = usuarios.get(i);

            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        throw new Exception("NO EXISTE EL USUARIO!");
    }


/*
    private void cargarForzada(){
        usuariosT usuarioForzado = new usuariosT();
        String pass = "Carbonis";

        usuarioForzado.setEmail("superUsuario@us.com");

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d,32,64);

        String hashPass = argon2.hash(1,1024,1,pass);

        usuarioForzado.setContraseña(hashPass);

        this.usuarioTrepo.save(usuarioForzado);}

*/
}
