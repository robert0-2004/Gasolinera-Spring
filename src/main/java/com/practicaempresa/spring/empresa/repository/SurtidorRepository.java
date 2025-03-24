package com.practicaempresa.spring.empresa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practicaempresa.spring.empresa.entities.Surtidor;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurtidorRepository extends JpaRepository<Surtidor, Long> {
    Optional<Surtidor> findByCodigo(String codigo);
    List<Surtidor> findByActivo(boolean activo);
}
