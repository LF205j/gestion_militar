package com.defensa.gestion_militar.Repositorios;

import com.defensa.gestion_militar.Entity.Cuerpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo_Cuerpos extends JpaRepository<Cuerpo,Long> {
}
