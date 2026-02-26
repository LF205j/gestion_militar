package com.defensa.gestion_militar.Mappers;

import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.DTOs.UsuarioRegistroDTO;
import com.defensa.gestion_militar.Entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .tipoUsuario(usuario.getClass().getSimpleName().toUpperCase())
                .nombreCuartel(usuario.getCuartel() != null ? usuario.getCuartel().getNombre_cuartel() : "Sin asignar")
                .nombreCuerpo(usuario.getCuerpo() != null ? usuario.getCuerpo().getDenominacion_cuerpo() : "Sin asignar")
                .nombreCompania(usuario.getCompania() != null ? usuario.getCompania().getActividad_principal() : "Sin asignar")
                // MAPEAMOS LOS SERVICIOS: Extraemos los nombres de la tabla intermedia
                .nombresServicios(usuario.getServiciosRealizados() != null ?
                        usuario.getServiciosRealizados().stream()
                                .map(realiza -> realiza.getServicio().getNombre_servicio())
                                .collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }

    public void updateEntityFromRegistro(UsuarioRegistroDTO dto, Usuario usuario) {
        if (dto == null) return;

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasenia(dto.getContrasenia());

        // NOTA: Los servicios generalmente no se actualizan directamente aquí
        // porque requieren crear registros en la tabla intermedia 'RealizaServicio'.
        // Eso se suele hacer en el Service usando un 'Repo_RealizaServicio'.
    }
}
