package com.informatorio.tpJavaFinal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import com.informatorio.tpJavaFinal.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByFechaDeCreacionAfter(LocalDateTime fechaDesde);

    List<Usuario> findByCiudad(String ciudad);
}


/*
import java.util.ArrayList;

import com.informatorio.tpJavaFinal.entity.Usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    //otros métodos posibles: findByNombre(String nombre) o findByEmail(String email)
    public abstract ArrayList<Usuario> findByCiudad(String ciudad); 
       
}*/
