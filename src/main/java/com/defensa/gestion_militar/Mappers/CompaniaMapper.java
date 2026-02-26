package com.defensa.gestion_militar.Mappers;

import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.DTOs.UsuarioRegistroDTO;
import com.defensa.gestion_militar.Entity.Compania;
import com.defensa.gestion_militar.Entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CompaniaMapper {

    public CompaniaDTO toDTO(Compania compania) {
        if (compania == null) return null;

        return CompaniaDTO.builder()
                .id(compania.getId())
                .actividadPrincipal(compania.getActividad_principal())
                .build();
    }

    public Compania toEntity(CompaniaDTO dto) {
        if (dto == null) return null;

        return Compania.builder()
                .actividad_principal(dto.getActividadPrincipal())
                .build();
    }
}
