package com.practicaempresa.spring.empresa.controllers;


import com.practicaempresa.spring.empresa.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practicaempresa.spring.empresa.entities.Producto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    
    @PostMapping("/inicializar")
    public ResponseEntity<Void> crear() {
        productoService.inicializarProductos();
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosProductos() {
        return ResponseEntity.ok(productoService.obtenerTodosProductos());
    }
    
    @PutMapping("/{id}/precio")
    public ResponseEntity<Producto> actualizarPrecio(
            @PathVariable Long id,
            @RequestParam BigDecimal nuevoPrecio) {
        try {
            Producto producto = productoService.actualizarPrecio(id, nuevoPrecio);
            return ResponseEntity.ok(producto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

