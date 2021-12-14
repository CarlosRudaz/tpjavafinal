package com.informatorio.tpJavaFinal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.informatorio.tpJavaFinal.entity.Usuario;
import com.informatorio.tpJavaFinal.repository.UsuarioRepository;
import com.informatorio.tpJavaFinal.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;
// import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    

    @PostMapping
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde) {
        if (fechaDesde != null) {
            List<Usuario> usuarios = usuarioRepository.findByFechaDeCreacionAfter(fechaDesde.atStartOfDay());
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    /* @GetMapping( path = "/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return usuarioRepository.findById(id);
        
    } */
    

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        final boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok){
            return "Se eliminó el usuario con id " + id;
        }else{
            return "No se encuentra el usuario con id " + id;
        }
    }


    @GetMapping( path = "/{ciudad}")
    public List<Usuario> obtenerUsuarioPorCiudad(@PathVariable("ciudad") String ciudad){
        return this.obtenerUsuarioPorCiudad(ciudad);
    } 
    
}




/* import java.util.ArrayList;
import java.util.Optional;

import com.informatorio.tpJavaFinal.entity.Usuario;
import com.informatorio.tpJavaFinal.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<Usuario> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping( path = "/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping( path = "/{ciudad}")
    public ArrayList<Usuario> obtenerUsuarioPorCiudad(@PathVariable("ciudad") String ciudad){
        return this.usuarioService.obtenerPorCiudad(ciudad);
    } 

   

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok){
            return "Se eliminó el usuario con id " + id;
        }else{
            return "No se encuentra el usuario con id " + id;
        }
    }

}

 */
