package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Entity.RealizaServicio;
import com.defensa.gestion_militar.Repositorios.Repo_RealizarServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealizarServicioService {

    @Autowired
    private Repo_RealizarServicio repo_realizarServicio;


    public List<RealizaServicio> obtenerTodosLosServiciosPorId(Long id){
       return repo_realizarServicio.findByUsuarioId(id);

    }


}
