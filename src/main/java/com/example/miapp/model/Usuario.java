package com.example.miapp.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@JsonPropertyOrder({"id", "nombre", "email", "contrasena"})
@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String nombre;
    private String email;

    //Oculta la contraseña en las respuestas JSON (pero se guarda en la base de datos)
    @JsonIgnore
    private String contrasena;
}