package com.argentinaPrograma.argProgApi.servicios;

import com.argentinaPrograma.argProgApi.entidades.imagenesT;
import com.argentinaPrograma.argProgApi.repository.imagenesTrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class imagenesService {

    @Autowired
    private imagenesTrepository imgRepo;


    public byte[] devolverIMG(Integer id) {

        if (this.imgRepo.existsById(id)) {
            return this.imgRepo.findById(id).get().getImgenbits();
        }else {
            return null;
        }
    }


    public Boolean eliminarIMG(Integer id) {

        if (this.imgRepo.existsById(id)) {
            this.imgRepo.deleteById(id);
            return true;

        } else {

            return false;
        }

    }

    public Boolean crearIMG(Integer idProyecto, MultipartFile img) {

        try {
            imagenesT imgNueva = new imagenesT();
            imgNueva.setNombreimg(img.getOriginalFilename());
            imgNueva.setImgenbits(img.getBytes());
            imgNueva.setCreada_por_id(idProyecto);

            this.imgRepo.save(imgNueva);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR AL GUARDAR!");
            return false;
        }
    }

}
