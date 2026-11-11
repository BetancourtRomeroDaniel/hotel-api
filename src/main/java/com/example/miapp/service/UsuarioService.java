package com.example.miapp.service;

import com.example.miapp.exception.ResourceNotFoundException;
import com.example.miapp.dto.UsuarioRequestDTO;
import com.example.miapp.dto.UsuarioResponseDTO;
import com.example.miapp.mapper.UsuarioMapper;
import com.example.miapp.model.Usuario;
import com.example.miapp.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // CREAR
    public UsuarioResponseDTO crear(UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }

    // Listar
    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    
    // Obtener por Id
    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado"
                ));

        return UsuarioMapper.toDTO(usuario);
    }

    // Actualizar
    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado"
                ));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());

        usuarioRepository.save(usuario);

        return UsuarioMapper.toDTO(usuario);
    }


    // Eliminar
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Usuario no encontrado"
            ));

        usuarioRepository.delete(usuario);
    }
}