package com.argentinaPrograma.argProgApi.servicios;

import com.argentinaPrograma.argProgApi.dto.estYexpDTO;
import com.argentinaPrograma.argProgApi.entidades.estudiosYexperienciaT;
import com.argentinaPrograma.argProgApi.entidades.tiposT;
import com.argentinaPrograma.argProgApi.repository.estudiosYexperienciaTrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class estudiosYexperienciaService {

    private final String RUTA_ABSOLUTA = "C://Users//gabriel//Desktop//https//portafolio con front y back argProg//userImgs";

    @Autowired
    private estudiosYexperienciaTrepository estYexpRepo;

    public List<estYexpDTO> traerTodo(String tipo) {
        List<estudiosYexperienciaT> listaEstExp = estYexpRepo.findAll();

        List<estYexpDTO> filtrados = new ArrayList<estYexpDTO>();

        for (Integer i = 0; i < listaEstExp.size(); i++) {

            estudiosYexperienciaT estOexp = listaEstExp.get(i);
            if (estOexp.getTipo().getTipo().equals(tipo)) {

                estYexpDTO estYexp = new estYexpDTO(
                        estOexp.getId(),
                        estOexp.getTitulo(),
                        estOexp.getDescripcion(),
                        estOexp.getTipo().getTipo());

                filtrados.add(estYexp);
            }
        }

        return filtrados;
    }

    public void eliminar(Integer id) {

        this.estYexpRepo.deleteById(id);
    }

    public void editSinImg(estYexpDTO expActualizadaSF) {

        tiposT tipo = new tiposT();
        if (expActualizadaSF.getTipo().equals("estudio")) {
            tipo.setId(1);
        } else {
            tipo.setId(2);
        }

        estudiosYexperienciaT expActualizada = new estudiosYexperienciaT(
                expActualizadaSF.getId(),
                expActualizadaSF.getTitulo(),
                expActualizadaSF.getDescripcion(),
                this.estYexpRepo.findById(expActualizadaSF.getId()).get().getUrl(),
                tipo
        );

        this.estYexpRepo.save(expActualizada);

    }

    public void editarConImagen(estYexpDTO datos, MultipartFile img) throws IOException {

        byte[] imgEnbits = img.getBytes();

        tiposT tipo = new tiposT();
        if (datos.getTipo().equals("estudio")) {
            tipo.setId(1);
            tipo.setTipo(datos.getTipo());
        } else {
            tipo.setId(2);
            tipo.setTipo(datos.getTipo());
        }

        estudiosYexperienciaT nuevosDatos = new estudiosYexperienciaT(
                datos.getId(),
                datos.getTitulo(),
                datos.getDescripcion(),
                imgEnbits,
                tipo);

        this.estYexpRepo.save(nuevosDatos);
    }

    public byte[] obtenerImg(Integer url) {
        return this.estYexpRepo.findById(url).get().getUrl();
    }


    public void crear(estYexpDTO nuevo, MultipartFile imgN, String Ntipo) {

        tiposT tipo = new tiposT();
        tipo.setTipo(Ntipo);

        if (Ntipo.equals("estudio")) {
            tipo.setId(1);
        } else {
            tipo.setId(2);
        }

        try {
            byte[] imgEnBist = imgN.getBytes();

            estudiosYexperienciaT guardarNuevo = new estudiosYexperienciaT();
            guardarNuevo.setTitulo(nuevo.getTitulo());
            guardarNuevo.setDescripcion(nuevo.getDescripcion());
            guardarNuevo.setTipo(tipo);
            guardarNuevo.setUrl(imgEnBist);

            this.estYexpRepo.save(guardarNuevo);

        } catch (Exception e) {
            System.out.println("ERROR AL CREAR NUEVO: "+nuevo.getTipo());
            System.out.println(e);
        }
    }

}
