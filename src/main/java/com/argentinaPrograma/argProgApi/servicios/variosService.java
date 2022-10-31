package com.argentinaPrograma.argProgApi.servicios;

import com.argentinaPrograma.argProgApi.dto.variosDTO;
import com.argentinaPrograma.argProgApi.entidades.variosT;
import com.argentinaPrograma.argProgApi.repository.variosTrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class variosService {

    @Autowired
    private variosTrepository variosTrep;

    public List<variosDTO> traerDatos() {
         List<variosT> datosDB = variosTrep.findAll();
        List<variosDTO> listaSinUrl = new ArrayList<variosDTO>();

         for(Integer i=0; i<datosDB.size();i++) {
             variosDTO datosSinURL = new variosDTO();

             datosSinURL.setId(datosDB.get(i).getId());
             datosSinURL.setNombre(datosDB.get(i).getNombre());
             datosSinURL.setDescripcion(datosDB.get(i).getDescripcion());
             datosSinURL.setTitulo(datosDB.get(i).getTitulo());

             listaSinUrl.add(datosSinURL);
         }
         return listaSinUrl;
    }

    public byte[] obtenerImg(Integer id) {

        byte[] imgEnbits = this.variosTrep.findById(id).get().getUrl();
        return imgEnbits;
    }



    public void guardarDatos(variosDTO datos){
        variosT datosNuevos = new variosT(1,datos.getNombre(),datos.getTitulo(),datos.getDescripcion());
        datosNuevos.setUrl(this.variosTrep.findById(datos.getId()).get().getUrl());
        variosTrep.save(datosNuevos);
    }


    public boolean guardarUserImg(variosDTO datosNuevos, MultipartFile img) throws IOException {

        byte[] bytesImg = img.getBytes();

        variosT datosConImg = new variosT();

        datosConImg.setId(1);
        datosConImg.setNombre(datosNuevos.getNombre());
        datosConImg.setDescripcion(datosNuevos.getDescripcion());
        datosConImg.setTitulo(datosNuevos.getTitulo());
        datosConImg.setUrl(bytesImg);

        variosTrep.save(datosConImg);
        return true;
    }


}

