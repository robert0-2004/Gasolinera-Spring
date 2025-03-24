package com.practicaempresa.spring.empresa.entities;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nombre;
    
    @Column(nullable = false)
    private String tipo;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    
    @Column(nullable = false)
    private LocalDate fechaActualizacionPrecio;
    
    // Constructor para facilitar la creación de productos
    public Producto(String nombre, String tipo, BigDecimal precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.fechaActualizacionPrecio = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public LocalDate getFechaActualizacionPrecio() {
        return fechaActualizacionPrecio;
    }

    public void setFechaActualizacionPrecio(LocalDate fechaActualizacionPrecio) {
        this.fechaActualizacionPrecio = fechaActualizacionPrecio;
    }

    
}