package com.defensa.gestion_militar.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "cuartel")
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"companias","usuarios"}) // Evita que el cuartel intente imprimir sus compañías
@NoArgsConstructor
@AllArgsConstructor
public class Cuartel extends Identificable{

    @Column(name = "nombre_cuartel" ,nullable = false, length = 100)
    private String nombre_cuartel;

    @Column(name = "ubicacion",nullable = false, length = 100)
    private String ubicacion;

    @OneToMany(mappedBy = "cuartel", cascade = CascadeType.ALL)
    private List<Usuario> usuarios; // Usamos List en lugar de ArrayList

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cuartel_compania", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_cuartel"),
            inverseJoinColumns = @JoinColumn(name = "id_compania")
    )
    private List<Compania> companias;
}
