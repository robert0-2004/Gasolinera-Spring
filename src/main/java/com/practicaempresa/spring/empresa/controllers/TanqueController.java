package com.practicaempresa.spring.empresa.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practicaempresa.spring.empresa.entities.Tanque;
import com.practicaempresa.spring.empresa.services.TanqueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tanques")
public class TanqueController {
    @Autowired
    private TanqueService tanqueService;
    
    @PostMapping
    public ResponseEntity<Tanque> crearTanque(@RequestBody Tanque tanque) {
        try {
            Tanque nuevoTanque = tanqueService.crearTanque(tanque);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTanque);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}/litros-disponibles")
    public ResponseEntity<Map<String, Integer>> obtenerLitrosDisponibles(@PathVariable Long id) {
        try {
            int litrosDisponibles = tanqueService.obtenerLitrosDisponibles(id);
            Map<String, Integer> response = new HashMap<>();
            response.put("litrosDisponibles", litrosDisponibles);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/litros-consumidos")
    public ResponseEntity<Map<String, Integer>> obtenerLitrosConsumidos(@PathVariable Long id) {
        try {
            int litrosConsumidos = tanqueService.obtenerLitrosConsumidos(id);
            Map<String, Integer> response = new HashMap<>();
            response.put("litrosConsumidos", litrosConsumidos);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/rellenar")
    public ResponseEntity<Tanque> rellenarTanque(@PathVariable Long id, @RequestParam int litros) {
        try {
            Tanque tanque = tanqueService.rellenarTanque(id, litros);
            return ResponseEntity.ok(tanque);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Tanque>> obtenerTodosTanques() {
        return ResponseEntity.ok(tanqueService.obtenerTodosTanques());
    }
}
