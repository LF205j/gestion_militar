package com.defensa.gestion_militar.Controllers;

import com.defensa.gestion_militar.DTOs.ServicioHistorialDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.Entity.RealizaServicio;
import com.defensa.gestion_militar.Entity.Servicios;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Services.RealizarServicioService;
import com.defensa.gestion_militar.Services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    RealizarServicioService realizarServicioService;

    @GetMapping("/{id}")
    public String verDatos(@PathVariable Long id, Model model){
//        Usuario usuarioAdmin=usuariosService.obtenerUsuarioLogueado();
//       UsuarioDTO usuario= usuariosService.obtenerUserPorId(usuarioAdmin.getId(),id);
//       List<RealizaServicio> serviciosList=realizarServicioService.obtenerTodosLosServiciosPorId(id);
        Usuario usuarioAdmin = usuariosService.obtenerUsuarioLogueado(); //
        UsuarioDTO usuario = usuariosService.obtenerUserPorId(usuarioAdmin.getId()); //

        // Convertir la lista de RealizaServicio a ServicioHistorialDTO
        List<ServicioHistorialDTO> serviciosList = realizarServicioService.obtenerTodosLosServiciosPorId(id)
                .stream()
                .map(rs -> ServicioHistorialDTO.builder()
                        .idRealizaServicio(rs.getId()) // Usando el ID heredado de Identificable
                        .nombreServicio(rs.getServicio().getNombre_servicio()) //
                        .realizado(rs.isRealizado())
                        .fecha(rs.getFechaRealizacion()) //
                        .build())
                .toList();
        model.addAttribute("usuario",usuario);
       model.addAttribute("servicios",serviciosList);
       return "home";
    }
    @PostMapping("/servicio/completar/{id}")
    public String completarServicio(@PathVariable Long id, @RequestParam Long usuarioId) {
        // 1. Llamamos al servicio para cambiar el estado
        realizarServicioService.marcarComoRealizado(id);

        // 2. Redirigimos a la misma página del usuario para ver los cambios
        return "home";
    }
}
