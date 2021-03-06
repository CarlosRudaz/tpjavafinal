package com.informatorio.tpJavaFinal.service;

import java.time.LocalDateTime;
import java.util.List;

import com.informatorio.tpJavaFinal.entity.Usuario;
import com.informatorio.tpJavaFinal.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public Boolean eliminarUsuario(Long id) {
        
        try {
            Usuario eliminando = usuarioRepository.getById(id);
            eliminando.setActivo(false);
            usuarioRepository.save(eliminando);
            
            return true;
        } catch (Exception err) {
            return false;
        }
    }
        

    public List<Usuario> despuesDeFecha(LocalDateTime fechaDeCreacion) {
        if (fechaDeCreacion != null) {
            return usuarioRepository.findByFechaDeCreacionAfter(fechaDeCreacion);
        } else { 
            return usuarioRepository.findAll(); 
        }
    }

    public List<Usuario> obtenerPorCiudad(String ciudad) {
        return usuarioRepository.findByCiudad(ciudad);
    }

}


/* import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.informatorio.tpJavaFinal.entity.Usuario;
import com.informatorio.tpJavaFinal.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository; 

    public ArrayList<Usuario> obtenerUsuarios(){
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuarioNuevo){
        
        
        if (usuarioNuevo.getId() == 0){
            return usuarioRepository.save(usuarioNuevo);
            
        }else{
            usuarioNuevo.setFechaDeCreacion(obtenerPorId(usuarioNuevo.getId()).get().getFechaDeCreacion());

            return usuarioRepository.save(usuarioNuevo);
        }        
    }

    public Optional<Usuario> obtenerPorId(Long id){
        //utilizamos una clase "optional" para el caso en que id no exista, no se rompa el programa.
        return usuarioRepository.findById(id);
    }

    public List<Usuario> obtenerPorCiudad(String ciudad){
        return usuarioRepository.findByCiudad(ciudad);
    }

    public boolean eliminarUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
    
} */