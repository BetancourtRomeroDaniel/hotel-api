package com.example.miapp.controller;

import com.example.miapp.dto.ReservaRequestDTO;
import com.example.miapp.dto.ReservaResponseDTO;
import com.example.miapp.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Tag(
    name = "Reservas",
    description = "Operaciones para gestionar las reservas de habitaciones"
)
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @Operation(
        summary = "Crear reserva",
        description = "Registra una reserva asociando un usuario y una habitación"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reserva creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> crear(@Valid @RequestBody ReservaRequestDTO dto) {
        return ResponseEntity.ok(reservaService.crear(dto));
    }
    
    @Operation(
        summary = "Listar reservas",
        description = "Obtiene todas las reservas registradas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listar() {
        return ResponseEntity.ok(reservaService.listar());
    }

    @Operation(
        summary = "Obtener reserva",
        description = "Obtiene una reserva específica por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reserva encontrada correctamente"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.obtenerPorId(id));
    }

    @Operation(
        summary = "Actualizar reserva",
        description = "Actualiza los datos de una reserva existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reserva actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ReservaRequestDTO dto) {

        return ResponseEntity.ok(reservaService.actualizar(id, dto));
    }
    
    @Operation(
        summary = "Eliminar reserva",
        description = "Elimina una reserva mediante su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Reserva eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Reserva no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
