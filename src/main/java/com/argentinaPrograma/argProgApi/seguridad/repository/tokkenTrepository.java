package com.argentinaPrograma.argProgApi.seguridad.repository;

import com.argentinaPrograma.argProgApi.seguridad.entidades.tokkenT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tokkenTrepository extends JpaRepository<tokkenT,Integer> {



}
