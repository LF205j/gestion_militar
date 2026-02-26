package com.defensa.gestion_militar.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "cuerpo")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"usuarios"}) // Evita que el cuartel intente imprimir sus compañías

public class Cuerpo extends Identificable{

    @Column(name="denominacion_cuerpo",nullable = false)
    private String denominacion_cuerpo;

    @OneToMany(mappedBy = "cuerpo", cascade = CascadeType.ALL)
    private List<Usuario> usuarios; // Usamos List en lugar de ArrayList
}
