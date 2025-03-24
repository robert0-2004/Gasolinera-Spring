package com.practicaempresa.spring.empresa.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "tanques")
public class Tanque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String codigo;
    
    @Column(nullable = false)
    private Integer capacidadTotal = 10000; // Capacidad en litros
    
    @Column(nullable = false)
    private Integer litrosDisponibles = 10000; // Inicialmente lleno
    
    @Column(nullable = false)
    private Integer litrosConsumidos = 0;
    
    @OneToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
    
    public Tanque(String codigo, Producto producto) {
        this.codigo = codigo;
        this.producto = producto;
    }
    
    // Método para consumir combustible del tanque
    public void consumirCombustible(int litros) {
        if (litros > this.litrosDisponibles) {
            throw new IllegalArgumentException("No hay suficiente combustible en el tanque");
        }
        this.litrosDisponibles -= litros;
        this.litrosConsumidos += litros;
    }
    
    // Método para rellenar el tanque
    public void rellenarTanque(int litros) {
        if (this.litrosDisponibles + litros > this.capacidadTotal) {
            throw new IllegalArgumentException("La cantidad excede la capacidad del tanque");
        }
        this.litrosDisponibles += litros;
    }
    
    // Método para obtener litros disponibles
    public int getLitrosDisponibles() {
        return this.litrosDisponibles;
    }
    
    // Método para obtener litros consumidos
    public int getLitrosConsumidos() {
        return this.litrosConsumidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(Integer capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public void setLitrosDisponibles(Integer litrosDisponibles) {
        this.litrosDisponibles = litrosDisponibles;
    }

    public void setLitrosConsumidos(Integer litrosConsumidos) {
        this.litrosConsumidos = litrosConsumidos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
}
