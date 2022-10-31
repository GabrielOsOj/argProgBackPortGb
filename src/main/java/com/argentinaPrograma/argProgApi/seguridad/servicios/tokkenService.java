package com.argentinaPrograma.argProgApi.seguridad.servicios;
import com.argentinaPrograma.argProgApi.seguridad.entidades.tokkenT;

import com.argentinaPrograma.argProgApi.seguridad.repository.tokkenTrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class tokkenService{

    @Autowired
    private tokkenTrepository tokken;

    public tokkenService() {
    }

    public String TokkenGen(Integer idUsuario , Integer largo){


        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Integer tokkenLong = largo;
        String tokkenReady= "";

        for (Integer i=0; i<tokkenLong;i++){
            Integer numeroRandom = Math.toIntExact(Math.round(Math.random() *(chars.length()-1)));
            tokkenReady+= chars.substring(numeroRandom,numeroRandom+1);
        }

        tokkenT nuevoTokken = new tokkenT(tokkenReady,idUsuario);

        this.tokken.save(nuevoTokken);

        return tokkenReady;
    }


    public Boolean tokkenChecker(String tokkenEntrante,String tokkenGuardado){

        return tokkenGuardado.equals(tokkenEntrante);

    }

    public Boolean tokkenDestroy(Integer id){
        try {
            this.tokken.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("NO SE PUDE ELIMINAR EL TOKKEN!");
            return false;
        }
    }




}
