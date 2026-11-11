package com.example.miapp.mapper;

import com.example.miapp.dto.HotelRequestDTO;
import com.example.miapp.dto.HotelResponseDTO;
import com.example.miapp.model.Hotel;

public class HotelMapper {

    public static HotelResponseDTO toDTO(Hotel hotel) {
        HotelResponseDTO dto = new HotelResponseDTO();
        dto.setId(hotel.getId());
        dto.setNombre(hotel.getNombre());
        dto.setDireccion(hotel.getDireccion());
        dto.setCiudad(hotel.getCiudad());
        return dto;
    }

    public static Hotel toEntity(HotelRequestDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setNombre(dto.getNombre());
        hotel.setDireccion(dto.getDireccion());
        hotel.setCiudad(dto.getCiudad());
        return hotel;
    }

}
