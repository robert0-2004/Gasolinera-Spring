package com.practicaempresa.spring.empresa.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practicaempresa.spring.empresa.entities.Servicio;
import com.practicaempresa.spring.empresa.services.ServicioService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class  ServicioController   {
    @Autowired
    private ServicioService servicioService;
    
    @PostMapping
    public ResponseEntity<Servicio> registrarServicio(
            @RequestParam Long surtidorId,
            @RequestParam Long productoId,
            @RequestParam Integer volumenLitros,
            @RequestParam(required = false) String infoVehiculo) {
        try {
            Servicio servicio = servicioService.registrarServicio(surtidorId, productoId, volumenLitros, infoVehiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(servicio);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable Long id) {
        try {
            Servicio servicio = servicioService.obtenerServicioPorId(id);
            return ResponseEntity.ok(servicio);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/por-fechas")
    public ResponseEntity<List<Servicio>> obtenerServiciosPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(servicioService.obtenerServiciosPorFechas(inicio, fin));
    }
    
    @GetMapping("/por-producto/{productoId}")
    public ResponseEntity<List<Servicio>> obtenerServiciosPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(servicioService.obtenerServiciosPorProducto(productoId));
    }
    
    @GetMapping("/por-surtidor/{surtidorId}")
    public ResponseEntity<List<Servicio>> obtenerServiciosPorSurtidor(@PathVariable Long surtidorId) {
        return ResponseEntity.ok(servicioService.obtenerServiciosPorSurtidor(surtidorId));
    }
}
