package com.defensa.gestion_militar.Repositorios;

import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repo_Cuarteles extends JpaRepository<Cuartel,Long> {
    Optional<Cuartel> findById(Long id);
}
