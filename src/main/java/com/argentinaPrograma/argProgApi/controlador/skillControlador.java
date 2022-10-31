package com.argentinaPrograma.argProgApi.controlador;

import com.argentinaPrograma.argProgApi.dto.skillsDTO;
import com.argentinaPrograma.argProgApi.entidades.skillsT;
import com.argentinaPrograma.argProgApi.seguridad.dto.usuarioTokkenDTO;
import com.argentinaPrograma.argProgApi.seguridad.servicios.usuariosService;
import com.argentinaPrograma.argProgApi.servicios.skillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://argentinaprogramagabosoj.firebaseapp.com/home",
        "https://argentinaprogramagabosoj.web.app/home"})
public class skillControlador {

    @Autowired
    private skillsService skillSv;

    @Autowired
    private usuariosService userSv;

    @GetMapping("/skills/get")
    public List<skillsT> traerTodo() {

        return skillSv.traerDatos();

    }


    @DeleteMapping("/skills/{id}")
    public ResponseEntity<String> eliminarSkill(@RequestHeader("tokken") String tokkenEntrante,
                                                @RequestHeader("id") Integer idEntrante,
                                                @PathVariable(value = "id") Integer id) {

        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante, tokkenEntrante))) {
            skillSv.elimarDatos(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION", HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/skills/nuevo")
    public ResponseEntity<String> crearSkill(@RequestHeader("tokken") String tokkenEntrante,
                              @RequestHeader("id")Integer idEntrante,
                              @RequestBody skillsDTO skill) {

        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante,tokkenEntrante))) {
            skillSv.crearSkill(skill);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION",HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/skills/editar")
    public ResponseEntity<String> editarSkill(@RequestHeader("tokken") String tokkenEntrante,
                               @RequestHeader("id")Integer idEntrante,
                               @RequestBody skillsDTO skill) {

        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante,tokkenEntrante))) {
            skillSv.actualizar(skill);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION",HttpStatus.UNAUTHORIZED);
        }



    }

}
