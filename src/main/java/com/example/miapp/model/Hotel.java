package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data

public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String direccion;

    @OneToMany(mappedBy = "hotel")
    @JsonManagedReference
    private List<Habitacion> habitaciones;

}
