package com.argentinaPrograma.argProgApi.servicios;

import com.argentinaPrograma.argProgApi.dto.proyectosDTO;
import com.argentinaPrograma.argProgApi.entidades.proyectosT;
import com.argentinaPrograma.argProgApi.repository.proyectosTrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class proyectosService {

    @Autowired
    private proyectosTrepository proyRepo;

    public List<proyectosDTO> traerDatosSF() {
        List<proyectosT> datos = proyRepo.findAll();
        List<proyectosDTO> datosSF = new ArrayList<proyectosDTO>();

        for (Integer i = 0; i < datos.size(); i++) {
            proyectosT dato = datos.get(i);

            List<Integer> idImgs = new ArrayList<>();

            for (Integer n = 0; n < dato.getImagenes().size(); n++) {
                idImgs.add(dato.getImagenes().get(n).getId());
            }

            proyectosDTO datoSF = new proyectosDTO(dato.getId(),
                    dato.getTitulo(),
                    dato.getDescripcion(),
                    idImgs);

            datosSF.add(datoSF);
        }
        return datosSF;
    }

    public boolean editar(proyectosDTO editBody) {

        try {
            proyectosT editDatos = new proyectosT(editBody.getId(),
                    editBody.getTitulo(),
                    editBody.getDescripcion());

            this.proyRepo.save(editDatos);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public Integer crear(proyectosDTO nuevo) {

        try {
            proyectosT nuevoP = new proyectosT();
            nuevoP.setTitulo(nuevo.getTitulo());
            nuevoP.setDescripcion(nuevo.getDescripcion());

            this.proyRepo.save(nuevoP);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("NO SE PUDO GUARDAR EL NUEVO ELEMENTO");
            return null;
        }

        List<proyectosT> listaProyectos = this.proyRepo.findAll();

        return this.devolverID(listaProyectos,nuevo.getTitulo(),nuevo.getDescripcion());

    }


    private Integer devolverID(List<proyectosT> lista, String igualQue1, String igualQue2) {

        Integer id = null;

        for (Integer i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTitulo().equals(igualQue1) && lista.get(i).getDescripcion().equals(igualQue2)) {
                id = lista.get(i).getId();
                break;
            }
        }

        return id;
    }

    public Boolean eliminarP(Integer id){
        try {
            this.proyRepo.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            System.out.printf("ERROR EN EL SERVICIO!");
            return false;
        }

    }

}
