package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuartel;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuerpo;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionServicio;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.ServiciosDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.Servicios;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Mappers.ServiciosMapper;
import com.defensa.gestion_militar.Mappers.UsuarioMapper;
import com.defensa.gestion_militar.Repositorios.Repo_RealizarServicio;
import com.defensa.gestion_militar.Repositorios.Repo_Servicios;
import com.defensa.gestion_militar.Repositorios.Repo_Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    @Autowired
    private Repo_Servicios repo_serv;

    @Autowired
    private ServiciosMapper serviciosMapper;

    @Autowired
    private Repo_RealizarServicio repo_realizarServicio;

    @Autowired
    private Repo_Usuarios repoUsuarios;

    public List<ServiciosDTO> obtenerTodosLosServicios() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repo_serv.findAll().stream()
                .map(serviciosMapper::toDTO)
                .collect(Collectors.toList());
    }
    public ServiciosDTO obtenerServicioPorId(Long idServicio){

            return repo_serv.findById(idServicio)
                    .map(serviciosMapper::toDTO)
                    .orElse(null);

    }


    // GUARDAR (Crear desde cero)
    public void guardarServicio(ServiciosDTO dto) {
        Servicios entidad = serviciosMapper.toEntity(dto);
        repo_serv.save(entidad);
    }

    // ELIMINAR
    public void eliminarServicio(Long id) {

        repo_serv.deleteById(id);

    }

    public void editarServicio(Long idServicio,ServiciosDTO dto){
        Servicios servicios=repo_serv.findById(idServicio).orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        servicios.setDescripcion_servicio(dto.getDescripcion_servicio());
        servicios.setNombre_servicio(dto.getNombre_servicio());
        servicios.setRango(dto.getRango());
        repo_serv.save(servicios);
    }
}
