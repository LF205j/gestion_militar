package com.defensa.gestion_militar.Mappers;

import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.Entity.Compania;
import com.defensa.gestion_militar.Entity.Cuartel;
import org.springframework.stereotype.Component;

@Component
public class CuartelMapper {

    public CuartelDTO toDTO(Cuartel cuartel) {
        if (cuartel == null) return null;

        return CuartelDTO.builder()
                .id(cuartel.getId())
                .nombreCuartel(cuartel.getNombre_cuartel())
                .ubicacion(cuartel.getUbicacion())
                .build();
    }

    public Cuartel toEntity(CuartelDTO dto) {
        if (dto == null) return null;

        return Cuartel.builder()
                .nombre_cuartel(dto.getNombreCuartel())
                .ubicacion(dto.getUbicacion())
                .build();
    }
}

