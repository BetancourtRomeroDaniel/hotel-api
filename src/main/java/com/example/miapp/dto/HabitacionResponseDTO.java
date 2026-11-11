package com.example.miapp.dto;

import lombok.Data;

@Data
public class HabitacionResponseDTO {
    private Long id;
    private String numero;
    private Double precio;

    private String nombreHotel;
}
