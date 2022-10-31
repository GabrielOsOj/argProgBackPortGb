package com.argentinaPrograma.argProgApi.seguridad.controlador;

import com.argentinaPrograma.argProgApi.seguridad.dto.usuarioDTO;
import com.argentinaPrograma.argProgApi.seguridad.dto.usuarioTokkenDTO;
import com.argentinaPrograma.argProgApi.seguridad.entidades.tokkenT;
import com.argentinaPrograma.argProgApi.seguridad.entidades.usuariosT;
import com.argentinaPrograma.argProgApi.seguridad.servicios.usuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "https://argentinaprogramagabosoj.web.app")
public class usuariosControlador {

    @Autowired
    private usuariosService usuarioSv;


    @PostMapping(value = "login/iniciar")
    @ResponseBody
    public ResponseEntity<usuarioTokkenDTO> iniciarSesion(@RequestBody usuarioDTO user) {


        try {
            usuarioTokkenDTO tokkenEid = this.usuarioSv.iniciar(user);
            return new ResponseEntity<>(tokkenEid,HttpStatus.ACCEPTED);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new usuarioTokkenDTO(null,"CREDENCIALES INVALIDAS!"),
                    HttpStatus.BAD_REQUEST);
        }

    }



    @GetMapping(value = "login/cerrarSession")
    public ResponseEntity<String> cerrarSession(@RequestHeader("tokken") String tokkenEntrante,
                                                @RequestHeader("id")Integer idEntrante){

        try {
            this.usuarioSv.cerrarSesion(new usuarioTokkenDTO(idEntrante,tokkenEntrante));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>( "ERROR AL CERRAR SESION!",HttpStatus.BAD_REQUEST);
        }

    }





    /*  @GetMapping("privado")
        public boolean privado(){
        this.usuarioSv.cargarForzada();
        return true;}

    */

}
