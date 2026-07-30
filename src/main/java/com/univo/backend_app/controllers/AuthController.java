package com.univo.backend_app.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    // Llave secreta para firmar el token (En producción, esto va en el application.properties)
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String emailRecibido = credenciales.get("email");
        String passwordRecibido = credenciales.get("password");

        // Simulamos la BD (validación de credenciales)
        if ("admin@univo.edu.mx".equals(emailRecibido) && "12345".equals(passwordRecibido)) {
            // Si son correctas construimos el JWT
            String token = Jwts.builder()
                    .setSubject(emailRecibido)  // A quién pertenece
                    .claim("rol", "ADMIN") // Qué permisos tiene
                    .setIssuedAt(new Date())    // Fecha de creación
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Caduca en 1 día
                    .signWith(secretKey)
                    .compact();

            // Devolvemos el token con un código HTTP 200 (OK)
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            // Si fallan, devolvemos un error 401 (No autorizado)
            return ResponseEntity.status(401).body(Map.of("error","Credenciales incorrectas"));
        }
    }
}
