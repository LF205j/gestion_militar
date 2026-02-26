package com.defensa.gestion_militar.Repositorios;

import com.defensa.gestion_militar.Entity.Compania;
import com.defensa.gestion_militar.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repo_Companias  extends JpaRepository<Compania,Long > {
    Optional<Compania> findById(Long id);

}
