package com.example.miapp.mapper;

import com.example.miapp.dto.HabitacionRequestDTO;
import com.example.miapp.dto.HabitacionResponseDTO;
import com.example.miapp.model.Habitacion;
import com.example.miapp.model.Hotel;

public class HabitacionMapper {

    public static HabitacionResponseDTO toDTO(Habitacion habitacion) {
        HabitacionResponseDTO dto = new HabitacionResponseDTO();
        dto.setId(habitacion.getId());
        dto.setNumero(habitacion.getNumero());
        dto.setPrecio(habitacion.getPrecio());
        dto.setNombreHotel(habitacion.getHotel().getNombre());
        return dto;
    }

    public static Habitacion toEntity(HabitacionRequestDTO dto, Hotel hotel) {
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero(dto.getNumero());
        habitacion.setPrecio(dto.getPrecio());
        habitacion.setHotel(hotel);
        return habitacion;
    }

}
