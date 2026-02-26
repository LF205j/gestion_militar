package com.defensa.gestion_militar.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@Table(name = "servicios")
@EqualsAndHashCode(callSuper = true)
public class Servicios extends Identificable{

    @Column(name = "nombre_servicio",nullable = false)
    private String nombre_servicio;

    @Column(name = "descripcion_servicio",nullable = false)
    private String descripcion_servicio;

    @Column(name = "rango", nullable = false)
    private Integer rango; // 1: Alto Mando, 2: Intermedio, 3: Operativo

    @OneToMany(mappedBy = "servicio")
    private List<RealizaServicio> historialDeUso;
}
