package com.practicaempresa.spring.empresa.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practicaempresa.spring.empresa.entities.Producto;
import com.practicaempresa.spring.empresa.entities.Tanque;
import com.practicaempresa.spring.empresa.repository.ProductoRepository;
import com.practicaempresa.spring.empresa.repository.TanqueRepository;

import java.util.List;

@Service
public class TanqueService {
    @Autowired
    private TanqueRepository tanqueRepository;
    @Autowired
    private ProductoRepository productoRepository;
    
    
     //Crear un nuevo tanque
     
    @Transactional
    public Tanque crearTanque(Tanque tanque) {
        // Verificar que el código no exista
        if (tanqueRepository.findByCodigo(tanque.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un tanque con este código");
        }
        
        // Verificar que el producto exista
        if (tanque.getProducto() != null && tanque.getProducto().getId() != null) {
            Producto producto = productoRepository.findById(tanque.getProducto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
            tanque.setProducto(producto);
        }
        
        return tanqueRepository.save(tanque);
    }
    
    
    //Obtener litros disponibles en un tanque
     
    public int obtenerLitrosDisponibles(Long tanqueId) {
        Tanque tanque = tanqueRepository.findById(tanqueId)
                .orElseThrow(() -> new IllegalArgumentException("Tanque no encontrado"));
        
        return tanque.getLitrosDisponibles();
    }
    
    
     //Obtener litros consumidos de un tanque
     
    public int obtenerLitrosConsumidos(Long tanqueId) {
        Tanque tanque = tanqueRepository.findById(tanqueId)
                .orElseThrow(() -> new IllegalArgumentException("Tanque no encontrado"));
        
        return tanque.getLitrosConsumidos();
    }
    
    
    //Consumir combustible de un tanque
    
    @Transactional
    public Tanque consumirCombustible(Long tanqueId, int litros) {
        Tanque tanque = tanqueRepository.findById(tanqueId)
                .orElseThrow(() -> new IllegalArgumentException("Tanque no encontrado"));
        
        tanque.consumirCombustible(litros);
        return tanqueRepository.save(tanque);
    }
    
    
     //Rellenar un tanque
    
    @Transactional
    public Tanque rellenarTanque(Long tanqueId, int litros) {
        Tanque tanque = tanqueRepository.findById(tanqueId)
                .orElseThrow(() -> new IllegalArgumentException("Tanque no encontrado"));
        
        tanque.rellenarTanque(litros);
        return tanqueRepository.save(tanque);
    }
    
    
     //Obtener todos los tanques
    
    public List<Tanque> obtenerTodosTanques() {
        return tanqueRepository.findAll();
    }
}
