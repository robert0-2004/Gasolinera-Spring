package com.practicaempresa.spring.empresa.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practicaempresa.spring.empresa.entities.Producto;
import com.practicaempresa.spring.empresa.repository.ProductoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    
    
     //Inicializar los 4 productos básicos
     
    @Transactional
    public void inicializarProductos() {
        if (productoRepository.count() == 0) {
            // Crear los 4 productos básicos si no existen
            Producto gasolina95 = new Producto("Gasolina 95", "Gasolina", new BigDecimal("1.45"));
            Producto gasolinaPremium = new Producto("Gasolina Premium", "Gasolina", new BigDecimal("1.60"));
            Producto dieselNormal = new Producto("Diésel Normal", "Diésel", new BigDecimal("1.35"));
            Producto dieselPremium = new Producto("Diésel Premium", "Diésel", new BigDecimal("1.50"));
            
            productoRepository.save(gasolina95);
            productoRepository.save(gasolinaPremium);
            productoRepository.save(dieselNormal);
            productoRepository.save(dieselPremium);
        }
    }
    
    
     //Obtener todos los productos
     
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }
    
    
    // Actualizar precio de un producto
     
    @Transactional
    public Producto actualizarPrecio(Long productoId, BigDecimal nuevoPrecio) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        
        producto.setPrecio(nuevoPrecio);
        producto.setFechaActualizacionPrecio(LocalDate.now());
        
        return productoRepository.save(producto);
    }
}
