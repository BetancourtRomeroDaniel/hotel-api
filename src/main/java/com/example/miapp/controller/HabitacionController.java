package com.example.miapp.controller;

import com.example.miapp.dto.HabitacionRequestDTO;
import com.example.miapp.dto.HabitacionResponseDTO;
import com.example.miapp.service.HabitacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;



@RestController
@RequestMapping("/habitaciones")
@Tag(
    name = "Habitaciones",
    description = "Operaciones para gestionar las habitaciones de los hoteles"
)
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @Operation(
        summary = "Crear habitación",
        description = "Registra una habitación asociada a un hotel"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Habitación creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o el hotel no existe"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<HabitacionResponseDTO> crear(@Valid @RequestBody HabitacionRequestDTO dto) {
        return ResponseEntity.ok(habitacionService.crear(dto));
    }

    @Operation(
        summary = "Listar habitaciones",
        description = "Obtiene todas las habitaciones registradas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de habitaciones obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<HabitacionResponseDTO>> listar() {
        return ResponseEntity.ok(habitacionService.listar());
    }


    @Operation(
        summary = "Buscar habitación por ID",
        description = "Obtiene los datos de una habitación mediante su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Habitación encontrada correctamente"),
        @ApiResponse(responseCode = "404", description = "Habitación no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HabitacionResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.obtenerPorId(id));
    }

    @Operation(
        summary = "Actualizar habitación",
        description = "Actualiza la información de una habitación existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Habitación actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o el hotel no existe"),
        @ApiResponse(responseCode = "404", description = "Habitación no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HabitacionResponseDTO> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody HabitacionRequestDTO dto) {
        return ResponseEntity.ok(habitacionService.actualizar(id, dto));
    }

    @Operation(
        summary = "Eliminar habitación",
        description = "Elimina una habitación mediante su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Habitación eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Habitación no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        habitacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
