package com.defensa.gestion_militar.Mappers;

import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import org.springframework.stereotype.Component;



@Component
public class CuerpoMapper {

    // Entidad -> DTO (Para mostrar en la lista)
    public CuerpoDTO toDTO(Cuerpo cuerpo) {
        if (cuerpo == null) return null;

        return CuerpoDTO.builder()
                .id(cuerpo.getId()).denominacionCuerpo(cuerpo.getDenominacion_cuerpo())
                .build();
    }

    // DTO -> Entidad (Para guardar/agregar un nuevo registro)
    public Cuerpo toEntity(CuerpoDTO dto) {
        if (dto == null) return null;

        // Aquí usamos el @Builder que agregaste a la Entidad Cuerpo
        return Cuerpo.builder().denominacion_cuerpo(dto.getDenominacionCuerpo()).build();
    }
    public void updateEntity(Cuerpo cuerpoExistente, CuerpoDTO dto) {
        if (dto == null || cuerpoExistente == null) return;

        // Actualizamos los campos permitidos (todos excepto el ID)
        cuerpoExistente.setDenominacion_cuerpo(dto.getDenominacionCuerpo());

        // Si tuvieras más campos como 'tipo' o 'region', se añadirían aquí:
        // cuerpoExistente.setTipo(dto.getTipo());
    }
}

