package com.defensa.gestion_militar.Repositorios;

import com.defensa.gestion_militar.Entity.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_Servicios extends JpaRepository<Servicios, Long> {
    List<Servicios> findByRango(Integer rango);

    // Para mostrar servicios de un rango o menores (más jerarquía)
    List<Servicios> findByRangoGreaterThanEqual(Integer rango);
}
