package com.argentinaPrograma.argProgApi.seguridad.repository;

import com.argentinaPrograma.argProgApi.seguridad.entidades.usuariosT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usuariosTrepository extends JpaRepository<usuariosT,Integer> {
}
