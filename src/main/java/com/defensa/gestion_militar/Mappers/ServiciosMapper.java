package com.defensa.gestion_militar.Mappers;

import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.ServiciosDTO;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Servicios;
import org.springframework.stereotype.Component;

@Component
public class ServiciosMapper {
    public ServiciosDTO toDTO(Servicios servicio) {
        if (servicio == null) return null;
        return ServiciosDTO.builder().id(servicio.getId()).descripcion_servicio(servicio.getDescripcion_servicio()).nombre_servicio(servicio.getNombre_servicio()).rango(servicio.getRango()).build();
    }
    // RegistroDTO -> Entidad (Para guardar en la DB)
    // Nota: El Service se encargará de buscar el Cuartel/Cuerpo real por ID
    public Servicios toEntity(ServiciosDTO dto) {
        if (dto == null) return null;
        Servicios servicio=new Servicios();
        servicio.setId(dto.getId());
        servicio.setNombre_servicio(dto.getNombre_servicio());
        servicio.setDescripcion_servicio(dto.getDescripcion_servicio());
        servicio.setRango(dto.getRango());
        return servicio;
    }
}
