package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.ServiciosDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.Entity.Servicios;
import com.defensa.gestion_militar.Mappers.ServiciosMapper;
import com.defensa.gestion_militar.Mappers.UsuarioMapper;
import com.defensa.gestion_militar.Repositorios.Repo_RealizarServicio;
import com.defensa.gestion_militar.Repositorios.Repo_Servicios;
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

    public List<ServiciosDTO> obtenerTodosLosServicios() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repo_serv.findAll().stream()
                .map(serviciosMapper::toDTO)
                .collect(Collectors.toList());
    }
    public ServiciosDTO obtenerServicioPorId(Long id){
        return repo_serv.findById(id)
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

    public void editarServicio(Long id,ServiciosDTO dto){
        Servicios servicios=repo_serv.findById(id).orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        servicios.setDescripcion_servicio(dto.getDescripcion_servicio());
        servicios.setNombre_servicio(dto.getNombre_servicio());
        servicios.setRango(dto.getRango());
        repo_serv.save(servicios);
    }
}
