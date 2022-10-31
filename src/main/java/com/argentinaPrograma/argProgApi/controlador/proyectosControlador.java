package com.argentinaPrograma.argProgApi.controlador;

import com.argentinaPrograma.argProgApi.dto.proyectosDTO;
import com.argentinaPrograma.argProgApi.entidades.proyectosT;
import com.argentinaPrograma.argProgApi.seguridad.dto.usuarioTokkenDTO;
import com.argentinaPrograma.argProgApi.seguridad.servicios.usuariosService;
import com.argentinaPrograma.argProgApi.servicios.proyectosService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"https://argentinaprogramagabosoj.firebaseapp.com",
        "https://argentinaprogramagabosoj.web.app"})
public class proyectosControlador {

    @Autowired
    private proyectosService proyectoSv;

    @Autowired
    private usuariosService userSv;

    @GetMapping(value = "proyectos/datos")
    public List<proyectosDTO> traerDatos() {
        return this.proyectoSv.traerDatosSF();
    }


    @PutMapping(value = "proyectos/editarSF")
    public ResponseEntity<String> editarDatosSF(@RequestHeader("tokken") String tokkenEntrante,
                                                @RequestHeader("id") Integer idEntrante,
                                                @RequestBody proyectosDTO editBody) {


        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante, tokkenEntrante))) {
            this.proyectoSv.editar(editBody);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "proyectos/crear")
    public ResponseEntity<Integer> crearProyecto(@RequestHeader("tokken") String tokkenEntrante,
                                                 @RequestHeader("id")Integer idEntrante,
                                                 @RequestParam("Ntitulo") String titulo,
                                                 @RequestParam("Ndescripcion") String descripcion) {

        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante,tokkenEntrante))) {

            proyectosDTO nuevo = new proyectosDTO();
            nuevo.setTitulo(titulo);
            nuevo.setDescripcion(descripcion);
            Integer dato = this.proyectoSv.crear(nuevo);

            if (dato != null) {
                return new ResponseEntity<>(dato, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }





    }

    @DeleteMapping(value = "proyectos/eliminar/{id}")
    public ResponseEntity<String> eliminarProyecto(@RequestHeader("tokken") String tokkenEntrante,
                                                   @RequestHeader("id")Integer idEntrante,
                                                   @PathVariable(value = "id") Integer id) {

        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante, tokkenEntrante))) {

            Boolean borrado = this.proyectoSv.eliminarP(id);

            if (borrado) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION", HttpStatus.UNAUTHORIZED);
        }



    }

}

