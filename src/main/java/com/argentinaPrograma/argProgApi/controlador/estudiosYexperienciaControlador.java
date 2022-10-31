package com.argentinaPrograma.argProgApi.controlador;

import com.argentinaPrograma.argProgApi.dto.estYexpDTO;
import com.argentinaPrograma.argProgApi.seguridad.dto.usuarioTokkenDTO;
import com.argentinaPrograma.argProgApi.seguridad.servicios.usuariosService;
import com.argentinaPrograma.argProgApi.servicios.estudiosYexperienciaService;
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
@CrossOrigin("https://argentinaprogramagabosoj.web.app")
public class estudiosYexperienciaControlador {

    @Autowired
    private estudiosYexperienciaService estYexpSv;

    @Autowired
    private usuariosService userSv;

    //traer datos
    @GetMapping("/estYexp/{tipo}")
    public List<estYexpDTO> traerEstudios(@PathVariable(value = "tipo") String tipo) {
        return estYexpSv.traerTodo(tipo);
    }

    //traer foto
    @GetMapping("/estYexp/")
    public ResponseEntity imgRetorno(@RequestParam(value = "url") Integer url) {

        if (this.estYexpSv.obtenerImg(url) != null) {
            byte[] img = this.estYexpSv.obtenerImg(url);

            ByteArrayResource imgEnbits = new ByteArrayResource(img);

            return ResponseEntity.ok()
                    .contentLength(img.length)
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(imgEnbits);

        } else {
            return null;
        }
    }

    //crear
    @PostMapping("/estYexp/nuevo")
    public ResponseEntity<String> crear(@RequestHeader("tokken") String tokkenEntrante,
                                        @RequestHeader("id")Integer idEntrante,
                                        @RequestParam("Ntipo") String tipo,
                                        @RequestParam("Ntitulo") String titulo,
                                        @RequestParam("Ndescripcion") String descripcion,
                                        @RequestParam("file") MultipartFile imgNueva){

        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante,tokkenEntrante))) {

            estYexpDTO nuevo = new estYexpDTO();
            nuevo.setDescripcion(descripcion);
            nuevo.setTitulo(titulo);
            this.estYexpSv.crear(nuevo, imgNueva, tipo);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION",HttpStatus.UNAUTHORIZED);
        }

    };

    //eliminar x id
    @DeleteMapping("/estYexp/{id}")
    public ResponseEntity<String> eliminarEstOexp(@RequestHeader("tokken") String tokkenEntrante,
                                   @RequestHeader("id")Integer idEntrante,
                                   @PathVariable(value = "id") Integer id) {


        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante,tokkenEntrante))) {

            this.estYexpSv.eliminar(id);

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION",HttpStatus.UNAUTHORIZED);
        }


    }


    //editar sin foto
    @PutMapping("/estYexp/editarSF")
    public ResponseEntity<String> actualizarExp(@RequestHeader("tokken") String tokkenEntrante,
                                 @RequestHeader("id")Integer idEntrante,
                                 @RequestBody estYexpDTO datosActualizado) {

        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante,tokkenEntrante))) {
            this.estYexpSv.editSinImg(datosActualizado);

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION",HttpStatus.UNAUTHORIZED);
        }

    }


    //editar con foto
    @PostMapping("/estYexp/editarCF")
    public ResponseEntity<String> actualizarExpYfoto(@RequestHeader("tokken") String tokkenEntrante,
                                      @RequestHeader("id")Integer idEntrante,
                                      @RequestParam("id") Integer id,
                                      @RequestParam("titulo") String titulo,
                                      @RequestParam("descripcion") String descripcion,
                                      @RequestParam("tipo") String tipo,
                                      @RequestParam("file") MultipartFile imagen) throws IOException {

        if (this.userSv.validarTokken(new usuarioTokkenDTO(idEntrante,tokkenEntrante))) {
            estYexpDTO datos = new estYexpDTO(id, titulo, descripcion,tipo);

            this.estYexpSv.editarConImagen(datos, imagen);

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("TOKKEN INVALIDO O NO INICIO SESION",HttpStatus.UNAUTHORIZED);
        }




    }


}
