package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadGestionCompania;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuartel;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuerpo;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionServicio;
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

    public void AsignarCuartel( Long idUsuario, Long idCuartel){
        Usuario usuario=repoUsuario.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;

        Cuartel cuartel=repoCuartel.findById(idCuartel).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
        usuario.setCuartel(cuartel);
        repoUsuario.save(usuario);
//        Usuario ejecutor = repoUsuario.findById(idOficialLogueado)
//                .orElseThrow(() -> new RuntimeException("Oficial ejecutor no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad de gestionar cuarteles
//        if (ejecutor instanceof CapacidadGestionCuartel oficialConMando) {
//
//            // 3. La entidad Oficial valida la orden
//            oficialConMando.asignarCuartel(idUsuario, idCuartel);
//
//            // 4. Si la entidad no lanzó excepción, ejecutamos la lógica técnica
//            Usuario usuario = repoUsuario.findById(idUsuario)
//                    .orElseThrow(() -> new RuntimeException("Usuario a asignar no encontrado"));
//
//            Cuartel cuartel = repoCuartel.findById(idCuartel)
//                    .orElseThrow(() -> new RuntimeException("Cuartel de destino no encontrado"));
//
//            usuario.setCuartel(cuartel);
//            repoUsuario.save(usuario);
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: Tu rango no permite realizar asignaciones de cuartel.");
//
//        }
    }
    public void asignarCompaniaSuboficial(Long idEjecutor, Long idUser, Long idCompania) {
        Usuario ejecutor = repoUsuario.findById(idEjecutor).orElseThrow();
        Usuario destino = repoUsuario.findById(idUser).orElseThrow();

        if (ejecutor instanceof Suboficial suboficial) {

            // REGLA DE ORO: Si el que manda es Suboficial, el destino DEBE ser Soldado
            if (!(destino instanceof Soldado)) {
                throw new RuntimeException("Acceso Denegado: Los Suboficiales solo pueden asignar compañías a Soldados.");
            }

            // Llamamos al método que modificamos arriba
            //suboficial.asignarCompania(idUser, idCompania);

            // Persistencia
            Compania comp = repoCompania.findById(idCompania).orElseThrow();
            destino.setCompania(comp);
            repoUsuario.save(destino);
        }
    }
    public void AsignarCompania(Long idUsuario,Long idCompania){
        Usuario usuario=repoUsuario.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;

        Compania compania=repoCompania.findById(idCompania).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
        usuario.setCompania(compania);
        repoUsuario.save(usuario);
//        Usuario ejecutor = repoUsuario.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Oficial ejecutor no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad de gestionar cuarteles
//        if (ejecutor instanceof CapacidadGestionCompania oficialConMando) {
//
//            // 3. La entidad Oficial valida la orden
//            oficialConMando.asignarCompania(idUsuario, idCompania);
//
//            // 4. Si la entidad no lanzó excepción, ejecutamos la lógica técnica
//            Usuario usuario = repoUsuario.findById(idUsuario)
//                    .orElseThrow(() -> new RuntimeException("Usuario a asignar no encontrado"));
//
//            Compania compania = repoCompania.findById(idCompania)
//                    .orElseThrow(() -> new RuntimeException("Compania de destino no encontrado"));
//
//
//            usuario.setCompania(compania);
//            repoUsuario.save(usuario);
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: Tu rango no permite realizar asignaciones de cuartel.");
//
//        }


    }
    public void AsignarCuerpo(Long idUsuario,Long idCuerpo){
        Usuario usuario=repoUsuario.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;

        Cuerpo cuerpo=repoCuerpo.findById(idCuerpo).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
        usuario.setCuerpo(cuerpo);
        repoUsuario.save(usuario);
//        Usuario ejecutor = repoUsuario.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Oficial ejecutor no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad de gestionar cuarteles
//        if (ejecutor instanceof CapacidadGestionCuerpo oficialConMando) {
//
//            // 3. La entidad Oficial valida la orden
//            oficialConMando.asignarCuerpo(idUsuario, idCuerpo);
//
//            // 4. Si la entidad no lanzó excepción, ejecutamos la lógica técnica
//            Usuario usuario = repoUsuario.findById(idUsuario)
//                    .orElseThrow(() -> new RuntimeException("Usuario a asignar no encontrado"));
//
//            Cuerpo cuerpo = repoCuerpo.findById(idCuerpo)
//                    .orElseThrow(() -> new RuntimeException("Cuerpo de destino no encontrado"));
//
//
//            usuario.setCuerpo(cuerpo);
//            repoUsuario.save(usuario);
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: Tu rango no permite realizar asignaciones de cuartel.");
//
//        }

    }
    public void AsignarServicio(Long idAdmin,Long idUsuario,Long idServicio){
//        Usuario usuario=repoUsuario.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));;
//
//        Servicios servicio=repoServicio.findById(idServicio).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
//        RealizaServicio realizaServicio=new RealizaServicio();
//        realizaServicio.setUsuario(usuario);
//        realizaServicio.setServicio(servicio);
//        realizaServicio.setFechaRealizacion(LocalDate.now()); // Registro de hoy
//        // realizaServicio.setObservaciones("Asignación manual"); // Si tu entidad lo tiene
//
//        // 3. Guardar en el repo de la tabla intermedia
//        repoRealizarServicio.save(realizaServicio);
        Usuario ejecutor = repoUsuario.findById(idAdmin)
                .orElseThrow(() -> new RuntimeException("Oficial ejecutor no encontrado"));

        // 2. Verificamos si tiene la capacidad de gestionar cuarteles
        if (ejecutor instanceof CapacidadGestionServicio oficialConMando) {

            // 3. La entidad Oficial valida la orden
            oficialConMando.asignarServicio(idUsuario, idServicio);

            // 4. Si la entidad no lanzó excepción, ejecutamos la lógica técnica
            Usuario usuario = repoUsuario.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario a asignar no encontrado"));

            Servicios servicio = repoServicio.findById(idServicio)
                    .orElseThrow(() -> new RuntimeException("Servicio de destino no encontrado"));

            String rangoMilitar = "";
            if (usuario instanceof Oficial) {
                rangoMilitar = "OFICIAL";
            } else if (usuario instanceof Suboficial) {
                rangoMilitar = "SUBOFICIAL";
            } else if (usuario instanceof Soldado) {
                rangoMilitar = "SOLDADO";
            }
            Integer nivelServicio = servicio.getRango(); // Nivel 1, 2 o 3

            if (rangoMilitar.equals("SOLDADO") && nivelServicio != 1) {
                throw new RuntimeException("Denegado: Un Soldado solo puede realizar servicios de rango 1.");
            }
            if (rangoMilitar.equals("SUBOFICIAL") && nivelServicio != 2) {
                throw new RuntimeException("Denegado: Un Suboficial solo puede realizar servicios de rango 2.");
            }
            if (rangoMilitar.equals("OFICIAL") && nivelServicio != 3) {
                throw new RuntimeException("Denegado: Un Oficial solo puede realizar servicios de rango 3.");
            }
            RealizaServicio realizaServicio=new RealizaServicio();
            realizaServicio.setUsuario(usuario);
            realizaServicio.setServicio(servicio);
            realizaServicio.setFechaRealizacion(LocalDate.now());

            repoRealizarServicio.save(realizaServicio);

        } else {
            throw new RuntimeException("Acceso Denegado: Tu rango no permite realizar asignaciones de cuartel.");

        }


    }

    public void ReasignarCuartel(Long idNuevoCuartel,Long idViejoCuartel){
        Cuartel cuartelDestino=repoCuartel.findById(idNuevoCuartel).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));

        List<Usuario> usuarios=repoUsuario.findByCuartelId(idViejoCuartel);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("No hay usuarios en el cuartel de origen para reasignar");
        }
        for (Usuario usuario: usuarios){
            usuario.setCuartel(cuartelDestino);
        }
        repoUsuario.saveAll(usuarios);
    }
    public void ReasignarCuerpo(Long idNuevoCuerpo,Long idViejoCuerpo){
        Cuerpo cuerpoDestino=repoCuerpo.findById(idNuevoCuerpo).orElseThrow(() -> new RuntimeException("Cuerpo no encontrado"));

        List<Usuario> usuarios=repoUsuario.findByCuerpoId(idViejoCuerpo);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("No hay usuarios en el cuartel de origen para reasignar");
        }
        for (Usuario usuario: usuarios){
            usuario.setCuerpo(cuerpoDestino);
        }
        repoUsuario.saveAll(usuarios);
    }

    public void ReasignarCompania(Long idNuevoCompania,Long idViejoCompania){
        Compania companiaDestino=repoCompania.findById(idNuevoCompania).orElseThrow(() -> new RuntimeException("Compania no encontrada"));

        List<Usuario> usuarios=repoUsuario.findByCompaniaId(idViejoCompania);
        if (usuarios.isEmpty()) {
            throw new RuntimeException("No hay usuarios en el cuartel de origen para reasignar");
        }
        for (Usuario usuario: usuarios){
            usuario.setCompania(companiaDestino);
        }
        repoUsuario.saveAll(usuarios);
    }

//    public void ReasignarServicio(Long idNuevoServicio,Long idViejoServicio){
//        Compania companiaDestino=repoCompania.findById(idNuevoCompania).orElseThrow(() -> new RuntimeException("Compania no encontrada"));
//
//        List<Usuario> usuarios=repoUsuario.findByCompaniaId(idViejoCompania);
//        if (usuarios.isEmpty()) {
//            throw new RuntimeException("No hay usuarios en el cuartel de origen para reasignar");
//        }
//        for (Usuario usuario: usuarios){
//            usuario.setCompania(companiaDestino);
//        }
//        repoUsuario.saveAll(usuarios);
//    }

}