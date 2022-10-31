package com.argentinaPrograma.argProgApi.controlador;

import com.argentinaPrograma.argProgApi.seguridad.dto.usuarioTokkenDTO;
import com.argentinaPrograma.argProgApi.seguridad.servicios.usuariosService;
import com.argentinaPrograma.argProgApi.servicios.imagenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = {"https://argentinaprogramagabosoj.firebaseapp.com",
        "https://argentinaprogramagabosoj.web.app"})
public class imagenesControlador {

    @Autowired
    private usuariosService userSv;

    @Autowired
    private imagenesService imgSV;

    @GetMapping("imagen/{id}")
    public ResponseEntity traerIMG(@PathVariable(value = "id") Integer id) {

        if (this.imgSV.devolverIMG(id) != null) {
            ByteArrayResource imgFinal = new ByteArrayResource(this.imgSV.devolverIMG(id));

            return ResponseEntity.ok()
                    .contentLength(this.imgSV.devolverIMG(id).length)
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(imgFinal);
        } else {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = "imagen/del/{id}")
    public ResponseEntity<String> elimnarIMG(@RequestHeader("tokken") String tokkenEntrante,
                                             @RequestHeader("id") Integer idEntrante,
                                             @PathVariable(value = "id") Integer id) {


        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante, tokkenEntrante))) {

            if (this.imgSV.eliminarIMG(id)) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION", HttpStatus.UNAUTHORIZED);
        }


    }

    @PostMapping(value = "imagen/crear")
    public ResponseEntity<String> crearIMG(@RequestHeader("tokken") String tokkenEntrante,
                                               @RequestHeader("id")Integer idEntrante,
                                               @RequestParam("file") MultipartFile img,
                                               @RequestParam("id") String idProyecto) {

        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante, tokkenEntrante))) {

            if (this.imgSV.crearIMG(Integer.parseInt(idProyecto), img)) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION", HttpStatus.UNAUTHORIZED);
        }




    }

}
