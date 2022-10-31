package com.argentinaPrograma.argProgApi.servicios;

import com.argentinaPrograma.argProgApi.dto.skillsDTO;
import com.argentinaPrograma.argProgApi.entidades.skillsT;
import com.argentinaPrograma.argProgApi.repository.skillsTrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class skillsService {

    @Autowired
    private skillsTrepository SkillsTrepo;

    public List<skillsT> traerDatos() {
        return SkillsTrepo.findAll();
    }

    public void elimarDatos(Integer id){
        SkillsTrepo.deleteById(id);
    }

    public void crearSkill(skillsDTO skill){

        skillsT nuevaSkill = new skillsT(skill.getTitulo(),skill.getNivel());
        SkillsTrepo.save(nuevaSkill);
    }

    public void actualizar(skillsDTO skill){

        skillsT skillEditada= new skillsT(skill.getTitulo(),skill.getNivel());
        skillEditada.setIdSkill(skill.getIdSkill());

        SkillsTrepo.save(skillEditada);
    }


}
