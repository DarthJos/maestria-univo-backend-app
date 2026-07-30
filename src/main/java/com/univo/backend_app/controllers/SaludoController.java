package com.univo.backend_app.controllers;

import com.univo.backend_app.models.MensajeDTO;
import com.univo.backend_app.repositories.MensajeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mensajes")
@CrossOrigin(origins = "http://localhost:4200") // Permite al frontend acceder
public class SaludoController {

    private final MensajeRepository repository;

    // Inyección de dependencias por constructor (Buena práctica de la industria)
    public SaludoController(MensajeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MensajeDTO> listarMensajes() {
        return repository.findAll(); // Hace un SELECT * FROM mensajes
    }

    @PostMapping
    public MensajeDTO guardarMensaje(@RequestBody MensajeDTO nuevoMensaje) {
        return repository.save(nuevoMensaje); // Hace un INSERT INTO mensajes
    }
}

