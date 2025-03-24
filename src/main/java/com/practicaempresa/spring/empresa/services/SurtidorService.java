package com.practicaempresa.spring.empresa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practicaempresa.spring.empresa.entities.Producto;
import com.practicaempresa.spring.empresa.entities.Surtidor;
import com.practicaempresa.spring.empresa.repository.ProductoRepository;
import com.practicaempresa.spring.empresa.repository.SurtidorRepository;

import java.util.List;

@Service
public class SurtidorService {
    @Autowired
    private SurtidorRepository surtidorRepository;
    @Autowired
    private ProductoRepository productoRepository;
    
    
     //Dar de alta un nuevo surtidor
    
    @Transactional
    public Surtidor altaSurtidor(Surtidor surtidor) {
        // Verificar que el código no exista
        if (surtidorRepository.findByCodigo(surtidor.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un surtidor con este código");
        }
        
        // Guardar el surtidor
        return surtidorRepository.save(surtidor);
    }
    
    
    //Dar de baja un surtidor (desactivarlo)
     
    @Transactional
    public Surtidor bajaSurtidor(Long surtidorId) {
        Surtidor surtidor = surtidorRepository.findById(surtidorId)
                .orElseThrow(() -> new IllegalArgumentException("Surtidor no encontrado"));
        
        surtidor.setActivo(false);
        return surtidorRepository.save(surtidor);
    }
    
    
     //Obtener todos los surtidores activos
     
    public List<Surtidor> obtenerSurtidoresActivos() {
        return surtidorRepository.findByActivo(true);
    }
    
    
     //Agregar un producto a un surtidor
     
    @Transactional
    public Surtidor agregarProductoASurtidor(Long surtidorId, Long productoId) {
        Surtidor surtidor = surtidorRepository.findById(surtidorId)
                .orElseThrow(() -> new IllegalArgumentException("Surtidor no encontrado"));
        
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        
        surtidor.agregarProducto(producto);
        return surtidorRepository.save(surtidor);
    }
}