
package com.example.miapp.service;

import com.example.miapp.dto.HotelRequestDTO;
import com.example.miapp.dto.HotelResponseDTO;
import com.example.miapp.exception.ResourceNotFoundException;
import com.example.miapp.mapper.HotelMapper;
import com.example.miapp.model.Hotel;
import com.example.miapp.repository.HotelRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // Crear
    public HotelResponseDTO crear(HotelRequestDTO dto) {
        Hotel hotel = HotelMapper.toEntity(dto);
        hotelRepository.save(hotel);
        return HotelMapper.toDTO(hotel);
    }

    // Listar
    public List<HotelResponseDTO> listar() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelMapper::toDTO)
                .toList();
    }

    // Obtener por Id
    public HotelResponseDTO obtenerPorId(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hotel no encontrado"
                ));

        return HotelMapper.toDTO(hotel);
    }

    // Actualizar
    public HotelResponseDTO actualizar(Long id, HotelRequestDTO dto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hotel no encontrado"
                ));

        hotel.setNombre(dto.getNombre());
        hotel.setDireccion(dto.getDireccion());
        hotel.setCiudad(dto.getCiudad());

        hotelRepository.save(hotel);

        return HotelMapper.toDTO(hotel);
    }

    // Eliminar
    public void eliminar(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hotel no encontrado"
                ));

        hotelRepository.delete(hotel);
    }
}
