package com.example.miapp.repository;

import com.example.miapp.model.Habitacion;
import com.example.miapp.model.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long>{
    boolean existsByNumeroAndHotel(String numero, Hotel hotel);
}