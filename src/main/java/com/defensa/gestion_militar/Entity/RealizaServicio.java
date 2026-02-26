package com.defensa.gestion_militar.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "realiza_servicio")
@Data
@EqualsAndHashCode(callSuper = true)
public class RealizaServicio extends Identificable { // <--- Ahora hereda el 'codigo'

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicios servicio;

    @Column(name = "fecha_realizacion", nullable = false)
    private LocalDate fechaRealizacion;

    @Column(name = "observaciones")
    private String observaciones;
}