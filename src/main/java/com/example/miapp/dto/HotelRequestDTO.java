
package com.example.miapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class HotelRequestDTO {

    @NotBlank(message = "El nombre del hotel es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del hotel debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La dirección del hotel es obligatoria")
    @Size(max = 150, message = "La dirección no puede superar los 150 caracteres")
    private String direccion;

    @NotBlank(message = "La ciudad del hotel es obligatoria")
    @Size(min = 2, max = 80, message = "La ciudad debe tener entre 2 y 80 caracteres")
    private String ciudad;
}
