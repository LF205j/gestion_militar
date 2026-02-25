package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.DTOs.AsignacionDTO;
import com.defensa.gestion_militar.Entity.*;
import com.defensa.gestion_militar.Repositorios.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

// --- Imports de Spring Framework ---
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// --- Imports de Java Standard (Utilidades y Tiempo) ---
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

// --- Imports de tus Entidades ---
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.Compania;
import com.defensa.gestion_militar.Entity.Servicios;
import com.defensa.gestion_militar.Entity.RealizaServicio;

// --- Imports de tus Repositorios ---
import com.defensa.gestion_militar.Repositorios.Repo_Usuarios;
import com.defensa.gestion_militar.Repositorios.Repo_Cuarteles;
import com.defensa.gestion_militar.Repositorios.Repo_Cuerpos;
import com.defensa.gestion_militar.Repositorios.Repo_Companias;
import com.defensa.gestion_militar.Repositorios.Repo_Servicios;
import com.defensa.gestion_militar.Repositorios.Repo_RealizarServicio;

// --- Import de tu DTO (Asegúrate de que la ruta sea correcta) ---
import com.defensa.gestion_militar.DTOs.AsignacionDTO;
@Service
public class AsignacionService {

    @Autowired private Repo_Usuarios repoUsuario;
    @Autowired private Repo_Cuarteles repoCuartel;
    @Autowired private Repo_Cuerpos repoCuerpo;
    @Autowired private Repo_Companias repoCompania;
    @Autowired private Repo_Servicios repoServicio;
    @Autowired private Repo_RealizarServicio repoRealizarServicio;

    // --- MÉTODOS GET PARA EL FORMULARIO ---

    public void AsignarCuartel(Long idUsuario,Long idCuartel){
        Usuario usuario=repoUsuario.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;

        Cuartel cuartel=repoCuartel.findById(idCuartel).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
        usuario.setCuartel(cuartel);
        repoUsuario.save(usuario);

    }
    public void AsignarCompania(Long idUsuario,Long idCompania){
        Usuario usuario=repoUsuario.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;

        Compania compania=repoCompania.findById(idCompania).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
        usuario.setCompania(compania);
        repoUsuario.save(usuario);

    }
    public void AsignarCuerpo(Long idUsuario,Long idCuerpo){
        Usuario usuario=repoUsuario.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;

        Cuerpo cuerpo=repoCuerpo.findById(idCuerpo).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
        usuario.setCuerpo(cuerpo);
        repoUsuario.save(usuario);

    }
    public void AsignarServicio(Long idUsuario,Long idServicio){
        Usuario usuario=repoUsuario.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;

        Servicios servicio=repoServicio.findById(idServicio).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
        RealizaServicio realizaServicio=new RealizaServicio();
        realizaServicio.setUsuario(usuario);
        realizaServicio.setServicio(servicio);
        realizaServicio.setFechaRealizacion(LocalDate.now()); // Registro de hoy
        // realizaServicio.setObservaciones("Asignación manual"); // Si tu entidad lo tiene

        // 3. Guardar en el repo de la tabla intermedia
        repoRealizarServicio.save(realizaServicio);


    }
}