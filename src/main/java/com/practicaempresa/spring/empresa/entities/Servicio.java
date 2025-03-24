package com.practicaempresa.spring.empresa.entities;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "servicios")
public class Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime fechaServicio;
    
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "surtidor_id", nullable = false)
    private Surtidor surtidor;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal importeEuros;
    
    @Column(nullable = false)
    private Integer volumenLitros;
    
    private String infoVehiculo;

    
    
    public Servicio() {
    }

    public Servicio(Producto producto, Surtidor surtidor, BigDecimal importeEuros, Integer volumenLitros, String infoVehiculo) {
        this.fechaServicio = LocalDateTime.now();
        this.producto = producto;
        this.surtidor = surtidor;
        this.importeEuros = importeEuros;
        this.volumenLitros = volumenLitros;
        this.infoVehiculo = infoVehiculo;
    }
    
    // Método para obtener la fecha del servicio
    public LocalDateTime getFechaServicio() {
        return this.fechaServicio;
    }
    
    // Método para obtener el producto suministrado
    public Producto getProductoSuministrado() {
        return this.producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaServicio(LocalDateTime fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Surtidor getSurtidor() {
        return surtidor;
    }

    public void setSurtidor(Surtidor surtidor) {
        this.surtidor = surtidor;
    }

    public BigDecimal getImporteEuros() {
        return importeEuros;
    }

    public void setImporteEuros(BigDecimal importeEuros) {
        this.importeEuros = importeEuros;
    }

    public Integer getVolumenLitros() {
        return volumenLitros;
    }

    public void setVolumenLitros(Integer volumenLitros) {
        this.volumenLitros = volumenLitros;
    }

    public String getInfoVehiculo() {
        return infoVehiculo;
    }

    public void setInfoVehiculo(String infoVehiculo) {
        this.infoVehiculo = infoVehiculo;
    }

    
}