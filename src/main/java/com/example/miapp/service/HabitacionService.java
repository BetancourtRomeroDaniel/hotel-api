
package com.example.miapp.service;

import com.example.miapp.dto.HabitacionRequestDTO;
import com.example.miapp.dto.HabitacionResponseDTO;
import com.example.miapp.exception.ResourceNotFoundException;
import com.example.miapp.mapper.HabitacionMapper;
import com.example.miapp.model.Hotel;
import com.example.miapp.model.Habitacion;
import com.example.miapp.repository.HabitacionRepository;
import com.example.miapp.repository.HotelRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HotelRepository hotelRepository;

    public HabitacionService(
            HabitacionRepository habitacionRepository,
            HotelRepository hotelRepository) {
        this.habitacionRepository = habitacionRepository;
        this.hotelRepository = hotelRepository;
    }

    // Crear
    public HabitacionResponseDTO crear(HabitacionRequestDTO dto) {

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hotel no encontrado"
                ));

        if (habitacionRepository.existsByNumeroAndHotel(dto.getNumero(), hotel)) {
            throw new IllegalArgumentException(
                    "La habitación ya existe en este hotel"
            );
        }

        Habitacion habitacion = HabitacionMapper.toEntity(dto, hotel);
        habitacionRepository.save(habitacion);

        return HabitacionMapper.toDTO(habitacion);
    }

    // Listar
    public List<HabitacionResponseDTO> listar() {
        return habitacionRepository.findAll()
                .stream()
                .map(HabitacionMapper::toDTO)
                .toList();
    }

    // Obtener por Id
    public HabitacionResponseDTO obtenerPorId(Long id) {

        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Habitación no encontrada"
                ));

        return HabitacionMapper.toDTO(habitacion);
    }

    // Actualizar
    public HabitacionResponseDTO actualizar(
            Long id,
            HabitacionRequestDTO dto) {

        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Habitación no encontrada"
                ));

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Hotel no encontrado"
                ));

        if (!habitacion.getNumero().equals(dto.getNumero())
                && habitacionRepository.existsByNumeroAndHotel(
                        dto.getNumero(), hotel)) {

            throw new IllegalArgumentException(
                    "La habitación ya existe en este hotel"
            );
        }

        habitacion.setNumero(dto.getNumero());
        habitacion.setPrecio(dto.getPrecio());
        habitacion.setHotel(hotel);

        habitacionRepository.save(habitacion);

        return HabitacionMapper.toDTO(habitacion);
    }

    // Eliminar
    public void eliminar(Long id) {

        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Habitación no encontrada"
                ));

        habitacionRepository.delete(habitacion);
    }
}
