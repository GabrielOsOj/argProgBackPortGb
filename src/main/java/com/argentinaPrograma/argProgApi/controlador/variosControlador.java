package com.argentinaPrograma.argProgApi.controlador;

import com.argentinaPrograma.argProgApi.dto.variosDTO;
import com.argentinaPrograma.argProgApi.entidades.variosT;
import com.argentinaPrograma.argProgApi.seguridad.dto.usuarioTokkenDTO;
import com.argentinaPrograma.argProgApi.seguridad.servicios.usuariosService;
import com.argentinaPrograma.argProgApi.servicios.variosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = {"https://argentinaprogramagabosoj.firebaseapp.com",
        "https://argentinaprogramagabosoj.web.app"})
public class variosControlador {

    @Autowired
    private variosService variosSv;

    @Autowired
    private usuariosService userSv;

    @GetMapping("/varios/get")
    public List<variosDTO> getAll() {
        return variosSv.traerDatos();
    }

    @PutMapping("/varios/editar")
    public ResponseEntity<String> actualizar(@RequestBody variosDTO editInfo,@RequestHeader("tokken") String tokkenEntrante,
                              @RequestHeader("id")Integer idEntrante){


        if(this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante,tokkenEntrante))){
            this.variosSv.guardarDatos(editInfo);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>("NO SE INICIO SESION!",HttpStatus.UNAUTHORIZED);
        }

    }


    @PostMapping("/varios/especial")
    public ResponseEntity<String > imgUsuarioGuardar(@RequestHeader("tokken") String tokkenEntrante,
                                     @RequestHeader("id")Integer idEntrante,
                                     @RequestParam("id") Integer id,
                                     @RequestParam("titulo") String titulo,
                                     @RequestParam("nombre") String nombre,
                                     @RequestParam ("descripcion") String descripcion,
                                     @RequestParam("url") String url,
                                     @RequestParam ("file")MultipartFile imagen) throws IOException{


        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante,tokkenEntrante))) {
            variosDTO datosNuevos = new variosDTO(1, titulo, nombre, descripcion, url);
            this.variosSv.guardarUserImg(datosNuevos, imagen);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION",HttpStatus.UNAUTHORIZED);
        }

    }


    @GetMapping(value = "/varios{id}")
    public ResponseEntity<ByteArrayResource> imgTest(@RequestParam("id") Integer id) {

        try {
            ByteArrayResource imgEnbits = new ByteArrayResource(this.variosSv.obtenerImg(id));

            return ResponseEntity.ok()
                    .contentLength(this.variosSv.obtenerImg(id).length)
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(imgEnbits);
        } catch (Exception e) {
            System.out.println(e);
            return  null;
        }
    }



}
