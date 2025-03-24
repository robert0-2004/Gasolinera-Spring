package com.practicaempresa.spring.empresa.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practicaempresa.spring.empresa.entities.Producto;
import com.practicaempresa.spring.empresa.entities.Servicio;
import com.practicaempresa.spring.empresa.entities.Surtidor;
import com.practicaempresa.spring.empresa.entities.Tanque;
import com.practicaempresa.spring.empresa.repository.ProductoRepository;
import com.practicaempresa.spring.empresa.repository.ServicioRepository;
import com.practicaempresa.spring.empresa.repository.SurtidorRepository;
import com.practicaempresa.spring.empresa.repository.TanqueRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private SurtidorRepository surtidorRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private TanqueRepository tanqueRepository;
    @Autowired
    private TanqueService tanqueService;
    
   
     //Registrar un nuevo servicio
     
    @Transactional
    public Servicio registrarServicio(Long surtidorId, Long productoId, 
                                     Integer volumenLitros, String infoVehiculo) {
        // Verificar que el surtidor exista y esté activo
        Surtidor surtidor = surtidorRepository.findById(surtidorId)
                .orElseThrow(() -> new IllegalArgumentException("Surtidor no encontrado"));
        
        if (!surtidor.isActivo()) {
            throw new IllegalStateException("El surtidor no está activo");
        }
        
        // Verificar que el producto exista
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        
        // Verificar que el surtidor tenga el producto
        if (!surtidor.getProductos().contains(producto)) {
            throw new IllegalStateException("El surtidor no dispone de este producto");
        }
        
        // Buscar el tanque correspondiente al producto
        Tanque tanque = tanqueRepository.findByProductoId(productoId)
                .orElseThrow(() -> new IllegalArgumentException("No existe tanque para este producto"));
        
        // Verificar que haya suficiente combustible
        if (tanque.getLitrosDisponibles() < volumenLitros) {
            throw new IllegalStateException("No hay suficiente combustible en el tanque");
        }
        
        // Consumir combustible del tanque
        tanqueService.consumirCombustible(tanque.getId(), volumenLitros);
        
        // Calcular importe
        BigDecimal importeEuros = producto.getPrecio().multiply(BigDecimal.valueOf(volumenLitros));
        
        // Crear servicio
        Servicio servicio = new Servicio();
        servicio.setFechaServicio(LocalDateTime.now());
        servicio.setProducto(producto);
        servicio.setSurtidor(surtidor);
        servicio.setImporteEuros(importeEuros);
        servicio.setVolumenLitros(volumenLitros);
        servicio.setInfoVehiculo(infoVehiculo);
        
        return servicioRepository.save(servicio);
    }
    
    
      //Obtener un servicio por ID
     
    public Servicio obtenerServicioPorId(Long servicioId) {
        return servicioRepository.findById(servicioId)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado"));
    }
    
    
     //Obtener servicios por rango de fechas
    
    public List<Servicio> obtenerServiciosPorFechas(LocalDateTime inicio, LocalDateTime fin) {
        return servicioRepository.findByFechaServicio(inicio, fin);
    }
    
    
     // Obtener servicios por producto
    public List<Servicio> obtenerServiciosPorProducto(Long productoId) {
        return servicioRepository.findByProductoId(productoId);
    }
    
    
     //Obtener servicios por surtidor
     
    public List<Servicio> obtenerServiciosPorSurtidor(Long surtidorId) {
        return servicioRepository.findBySurtidorId(surtidorId);
    }
}