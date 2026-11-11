package com.example.miapp.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ReservaResponseDTO {
    private Long Id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private String nombreUsuario;
    private String numeroHabitacion;
}
