package com.practicaempresa.spring.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practicaempresa.spring.empresa.entities.Tanque;

import java.util.Optional;

@Repository
public interface TanqueRepository extends JpaRepository<Tanque, Long> {
    Optional<Tanque> findByCodigo(String codigo);
    Optional<Tanque> findByProductoId(Long productoId);
}
