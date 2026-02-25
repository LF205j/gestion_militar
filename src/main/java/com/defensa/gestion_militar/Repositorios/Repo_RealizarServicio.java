package com.defensa.gestion_militar.Repositorios;

import com.defensa.gestion_militar.Entity.RealizaServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface Repo_RealizarServicio extends JpaRepository<RealizaServicio,Long> {
    List<RealizaServicio> findByUsuarioId(Long usuarioId);

    // Obtener todos los servicios realizados en una fecha puntual
    List<RealizaServicio> findByFechaRealizacion(LocalDate fecha);
}
