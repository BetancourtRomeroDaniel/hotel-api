
package com.example.miapp.service;

import com.example.miapp.dto.ReservaRequestDTO;
import com.example.miapp.dto.ReservaResponseDTO;
import com.example.miapp.exception.ResourceNotFoundException;
import com.example.miapp.mapper.ReservaMapper;
import com.example.miapp.model.Reserva;
import com.example.miapp.model.Usuario;
import com.example.miapp.model.Habitacion;
import com.example.miapp.repository.ReservaRepository;
import com.example.miapp.repository.UsuarioRepository;
import com.example.miapp.repository.HabitacionRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HabitacionRepository habitacionRepository;

    public ReservaService(
            ReservaRepository reservaRepository,
            UsuarioRepository usuarioRepository,
            HabitacionRepository habitacionRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.habitacionRepository = habitacionRepository;
    }

    // LISTAR RESERVAS
    public List<ReservaResponseDTO> listar() {
        return reservaRepository.findAll()
                .stream()
                .map(ReservaMapper::toTDTO)
                .toList();
    }

    // OBTENER RESERVA POR ID
    public ReservaResponseDTO obtenerPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reserva no encontrada"
                ));

        return ReservaMapper.toTDTO(reserva);
    }

    // CREAR RESERVA
    public ReservaResponseDTO crear(ReservaRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado"
                ));

        Habitacion habitacion = habitacionRepository.findById(dto.getHabitacionId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Habitación no encontrada"
                ));

        if (dto.getFechaInicio().isAfter(dto.getFechaFin())) {
            throw new IllegalArgumentException(
                    "La fecha de inicio no puede ser mayor que la fecha fin"
            );
        }

        List<Reserva> reservasExistentes =
                reservaRepository.findByHabitacionIdAndFechaFinAfterAndFechaInicioBefore(
                        dto.getHabitacionId(),
                        dto.getFechaInicio(),
                        dto.getFechaFin()
                );

        if (!reservasExistentes.isEmpty()) {
            throw new IllegalArgumentException(
                    "La habitación ya está reservada en esas fechas"
            );
        }

        Reserva reserva = ReservaMapper.toEntity(dto, usuario, habitacion);

        reservaRepository.save(reserva);

        return ReservaMapper.toTDTO(reserva);
    }

    // ACTUALIZAR RESERVA
    public ReservaResponseDTO actualizar(Long id, ReservaRequestDTO dto) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reserva no encontrada"
                ));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado"
                ));

        Habitacion habitacion = habitacionRepository.findById(dto.getHabitacionId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Habitación no encontrada"
                ));

        if (dto.getFechaInicio().isAfter(dto.getFechaFin())) {
            throw new IllegalArgumentException(
                    "La fecha de inicio no puede ser mayor que la fecha fin"
            );
        }

        List<Reserva> reservasExistentes =
                reservaRepository.findByHabitacionIdAndFechaFinAfterAndFechaInicioBefore(
                        dto.getHabitacionId(),
                        dto.getFechaInicio(),
                        dto.getFechaFin()
                );

        boolean conflicto = reservasExistentes.stream()
                .anyMatch(r -> !r.getId().equals(id));

        if (conflicto) {
            throw new IllegalArgumentException(
                    "La habitación ya está reservada en esas fechas"
            );
        }

        reserva.setUsuario(usuario);
        reserva.setHabitacion(habitacion);
        reserva.setFechaInicio(dto.getFechaInicio());
        reserva.setFechaFin(dto.getFechaFin());

        reservaRepository.save(reserva);

        return ReservaMapper.toTDTO(reserva);
    }

    // ELIMINAR RESERVA
    public void eliminar(Long id) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reserva no encontrada"
                ));

        reservaRepository.delete(reserva);
    }
}
