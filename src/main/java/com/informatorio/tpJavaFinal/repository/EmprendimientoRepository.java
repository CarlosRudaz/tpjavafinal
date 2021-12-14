package com.informatorio.tpJavaFinal.repository;

import com.informatorio.tpJavaFinal.entity.Emprendimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Long> {
}