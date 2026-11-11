
package com.example.miapp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class HabitacionRequestDTO {

    @NotBlank(message = "El número de habitación es obligatorio")
    @Size(max = 20, message = "El número de habitación no puede superar los 20 caracteres")
    private String numero;

    @NotNull(message = "El precio de la habitación es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "El precio debe ser mayor que cero")
    private Double precio;

    @NotNull(message = "El ID del hotel es obligatorio")
    private Long hotelId;
}
