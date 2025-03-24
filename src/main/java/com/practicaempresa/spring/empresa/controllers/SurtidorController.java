package com.practicaempresa.spring.empresa.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practicaempresa.spring.empresa.entities.Surtidor;
import com.practicaempresa.spring.empresa.services.SurtidorService;

import java.util.List;

@RestController
@RequestMapping("/api/surtidores")
public class SurtidorController {
    @Autowired
    private SurtidorService surtidorService;
    
    @PostMapping
    public ResponseEntity<Surtidor> altaSurtidor(@RequestBody Surtidor surtidor) {
        try {
            Surtidor nuevoSurtidor = surtidorService.altaSurtidor(surtidor);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSurtidor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}/baja")
    public ResponseEntity<Surtidor> bajaSurtidor(@PathVariable Long id) {
        try {
            Surtidor surtidor = surtidorService.bajaSurtidor(id);
            return ResponseEntity.ok(surtidor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Surtidor>> obtenerSurtidoresActivos() {
        return ResponseEntity.ok(surtidorService.obtenerSurtidoresActivos());
    }
    
    @PostMapping("/{surtidorId}/productos/{productoId}")
    public ResponseEntity<Surtidor> agregarProductoASurtidor(
            @PathVariable Long surtidorId,
            @PathVariable Long productoId) {
        try {
            Surtidor surtidor = surtidorService.agregarProductoASurtidor(surtidorId, productoId);
            return ResponseEntity.ok(surtidor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}