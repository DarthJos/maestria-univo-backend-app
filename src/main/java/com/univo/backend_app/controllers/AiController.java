package com.univo.backend_app.controllers;

import com.univo.backend_app.services.InteligenciaArtificialService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/ia")
@CrossOrigin(origins = "http://localhost:4200")
public class AiController {

    private final InteligenciaArtificialService iaService;

    // Inyección de dependencias
    public AiController(InteligenciaArtificialService iaService) {
        this.iaService = iaService;
    }

    // Endpoint GET simple para probar
    @GetMapping("/consulta")
    public Map<String, String> preguntarIA(@RequestParam String pregunta){

        String respuesta = iaService.generarRespuestaSimple(pregunta);

        //Devolvemos un JSON estructurado con la respuesta
        return Map.of("respuesta",respuesta);
    }
}
