package com.practicaempresa.spring.empresa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practicaempresa.spring.empresa.entities.Servicio;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByProductoId(Long productoId);
    List<Servicio> findBySurtidorId(Long surtidorId);
    
    @Query("SELECT s FROM Servicio s WHERE s.fechaServicio BETWEEN :inicio AND :fin")
    List<Servicio> findByFechaServicio(LocalDateTime inicio, LocalDateTime fin);
}
