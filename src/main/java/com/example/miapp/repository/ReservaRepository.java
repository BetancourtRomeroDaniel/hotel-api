package com.example.miapp.repository;

import com.example.miapp.model.Reserva;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    List<Reserva> findByHabitacionIdAndFechaFinAfterAndFechaInicioBefore(
    Long habitacionId,
    LocalDate fechaFin,
    LocalDate fechaInicio
    );
}

