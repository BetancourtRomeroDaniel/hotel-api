package com.example.miapp.mapper;

import com.example.miapp.dto.ReservaRequestDTO;
import com.example.miapp.dto.ReservaResponseDTO;
import com.example.miapp.model.Reserva;
import com.example.miapp.model.Usuario;
import com.example.miapp.model.Habitacion;

public class ReservaMapper {

    public static ReservaResponseDTO toTDTO(Reserva reserva) {
        ReservaResponseDTO dto = new ReservaResponseDTO();
        dto.setId(reserva.getId());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setNombreUsuario(reserva.getUsuario().getNombre());
        dto.setNumeroHabitacion(reserva.getHabitacion().getNumero());
        return dto;
    }

    public static Reserva toEntity(ReservaRequestDTO dto, Usuario usuario, Habitacion habitacion) {
        Reserva reserva = new Reserva();
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFin(dto.getFechaFin());

        //Relaciones
        reserva.setUsuario(usuario);
        reserva.setHabitacion(habitacion);
        return reserva;
    }

}
