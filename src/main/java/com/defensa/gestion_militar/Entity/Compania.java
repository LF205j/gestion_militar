package com.defensa.gestion_militar.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "compania")
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"cuarteles","usuarios"})
@NoArgsConstructor
@AllArgsConstructor
public class Compania extends Identificable {

    @Column(name = "actividad_principal", nullable = false)
    private String actividad_principal;

    @ManyToMany(mappedBy = "companias")
    private List<Cuartel> cuarteles;

    @OneToMany(mappedBy = "compania", cascade = CascadeType.ALL)
    private List<Usuario> usuarios; // Usamos List en lugar de ArrayList
}