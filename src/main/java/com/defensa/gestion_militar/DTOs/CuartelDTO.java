package com.defensa.gestion_militar.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuartelDTO {
    private Long id; // El codigo de Identificable
    private String nombreCuartel;
    private String ubicacion;

    // No incluimos la lista de Usuarios ni Compañías para evitar recursividad
    // Si necesitás saber cuántas compañías tiene, podés agregar un contador:
    private Integer cantidadCompanias;
}
