package com.defensa.gestion_militar.DTOs;

import com.defensa.gestion_militar.Entity.RealizaServicio;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiciosDTO {

    private Long id;
    private String nombre_servicio;
    private String descripcion_servicio;
    private Integer rango; // 1: Alto Mando, 2: Intermedio, 3: Operativo
    //private List<RealizaServicio> historialDeUso;
}
