package com.informatorio.tpJavaFinal.repository;

import com.informatorio.tpJavaFinal.entity.Evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {   
}