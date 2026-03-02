package com.defensa.gestion_militar.Entity;



import com.defensa.gestion_militar.Capacidad.CapacidadServicio;
import com.defensa.gestion_militar.Capacidad.CapacidadVerSusDatos;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Data

@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"cuartel", "cuerpo", "compania","serviciosRealizados"}) // ¡Agregá esto!
public abstract class Usuario extends Identificable implements CapacidadServicio, CapacidadVerSusDatos {

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String email;

    @Column(name = "contrasenia", nullable = false, length = 255)
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "id_compania")
    private Compania compania;

    @ManyToOne
    @JoinColumn(name = "id_cuerpo")
    private Cuerpo cuerpo;

    @ManyToOne
    @JoinColumn(name = "id_cuartel")
    private Cuartel cuartel;

    @OneToMany(mappedBy = "usuario")
    private List<RealizaServicio> serviciosRealizados;

    public abstract void mostrarMenu();

}