package com.practicaempresa.spring.empresa.entities;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "surtidores")
public class Surtidor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String codigo;
    
    @Column(nullable = false)
    private String ubicacion;
    
    @Column(nullable = false)
    private boolean activo = true;
    
    @ManyToMany
    @JoinTable(
        name = "surtidor_producto",
        joinColumns = @JoinColumn(name = "surtidor_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private Set<Producto> productos = new HashSet<>();
    
    public Surtidor(String codigo, String ubicacion) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
    }
    
    // Método para agregar un producto al surtidor
    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }
    
    // Método para eliminar un producto del surtidor
    public void eliminarProducto(Producto producto) {
        this.productos.remove(producto);
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }


}