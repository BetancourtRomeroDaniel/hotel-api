package com.example.miapp.controller;

import com.example.miapp.dto.HotelRequestDTO;
import com.example.miapp.dto.HotelResponseDTO;
import com.example.miapp.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
@Tag(
    name = "Hoteles",
    description = "Operaciones para gestionar los hoteles del sistema"
)
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(
        summary = "Crear hotel",
        description = "Registra un nuevo hotel en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos del hotel inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    // POST - Crear hotel
    @PostMapping
    public ResponseEntity<HotelResponseDTO> crear(
            @Valid @RequestBody HotelRequestDTO dto) {

        return ResponseEntity.ok(hotelService.crear(dto));
    }

    @Operation(
        summary = "Listar hoteles",
        description = "Obtiene la lista de todos los hoteles registrados"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de hoteles obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    // GET - Listar hoteles
    @GetMapping
    public ResponseEntity<List<HotelResponseDTO>> listar() {
        return ResponseEntity.ok(hotelService.listar());
    }

    @Operation(
        summary = "Buscar hotel por ID",
        description = "Obtiene los datos de un hotel mediante su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel encontrado correctamente"),
        @ApiResponse(responseCode = "404", description = "Hotel no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    // GET - Obtener hotel por ID
    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> obtener(
            @PathVariable Long id) {

        return ResponseEntity.ok(hotelService.obtenerPorId(id));
    }

    @Operation(
        summary = "Actualizar hotel",
        description = "Actualiza la información de un hotel existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos del hotel inválidos"),
        @ApiResponse(responseCode = "404", description = "Hotel no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    // PUT - Actualizar hotel
    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody HotelRequestDTO dto) {

        return ResponseEntity.ok(hotelService.actualizar(id, dto));
    }

    @Operation(
        summary = "Eliminar hotel",
        description = "Elimina un hotel mediante su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Hotel eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Hotel no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    // DELETE - Eliminar hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        hotelService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
