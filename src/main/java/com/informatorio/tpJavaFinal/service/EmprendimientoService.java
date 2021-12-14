package com.informatorio.tpJavaFinal.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.informatorio.tpJavaFinal.entity.Emprendimiento;
import com.informatorio.tpJavaFinal.entity.Tag;
import com.informatorio.tpJavaFinal.entity.Usuario;
import com.informatorio.tpJavaFinal.repository.EmprendimientoRepository;
import com.informatorio.tpJavaFinal.repository.TagRepository;
import com.informatorio.tpJavaFinal.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprendimientoService {

    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TagRepository tagRepository;

    @Autowired
    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository, UsuarioRepository usuarioRepository,
                                 TagRepository tagRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.tagRepository = tagRepository;
    }

    public Emprendimiento guardarEmprendimiento(Long usuarioId, Emprendimiento emprendimiento) {
        Usuario usuario = usuarioRepository.getById(usuarioId);
        emprendimiento.setJefe(usuario);
        return emprendimientoRepository.save(emprendimiento);
    }
    public Emprendimiento eliminarEmprendimiento(Long id, Emprendimiento emprendimiento) {
        Emprendimiento emprendimientoEliminado = emprendimientoRepository.getById(id);
        emprendimientoEliminado.setActivo(false);
        return emprendimientoRepository.save(emprendimientoEliminado);
    }
    /* public Emprendimiento modificar(Long id, Emprendimiento emprendimiento) {
        Emprendimiento emprendimientoModificado = emprendimientoRepository.getById(id);
        if (!emprendimiento.getNombre().trim().isEmpty()) {
            emprendimientoModificado.setNombre(emprendimiento.getNombre()); }
        if (!emprendimiento.getDescripcion().trim().isEmpty()) {
            emprendimientoModificado.setDescripcion(emprendimiento.getDescripcion()); }
        if (!emprendimiento.getContenido().trim().isEmpty()) {
            emprendimientoModificado.setContenido(emprendimiento.getContenido()); }
        if (emprendimiento.getObjetivo() != null && emprendimiento.getObjetivo() > 0) {
            emprendimientoModificado.setObjetivo(emprendimiento.getObjetivo()); }
        if (emprendimiento.isPublicado() != true) { emprendimientoModificado.setPublicado(false); }
        if (emprendimiento.isPublicado() != false) { emprendimientoModificado.setPublicado(true); }
        if (emprendimiento.getUrl() != null) { emprendimientoModificado.setUrl(emprendimiento.getUrl()); }
        if (emprendimiento.getTags() != null) { emprendimientoModificado.setTags(emprendimiento.getTags()); }
        emprendimientoModificado.setUltimaModificacion(LocalDateTime.now());
        return emprendimientoRepository.save(emprendimientoModificado);
    } */
    public List<Emprendimiento> obtenerTodosLosEmprendimientos(String nombre) {
        if (nombre != null) { 
            Tag tag = tagRepository.findByNombre(nombre);
            return tag.getEmprendimientos();
        } else { 
            return emprendimientoRepository.findAll(); 
        }
    }
    public Stream<Emprendimiento> obtenerEmprendimientosNoPublicados() {
        return emprendimientoRepository.findAll().stream()
            .filter(Predicate.not(Emprendimiento::isPublicado));
    }
}
/* import java.util.ArrayList;


import com.informatorio.tpJavaFinal.dto.OperacionEmprendimiento;
import com.informatorio.tpJavaFinal.entity.Emprendimiento;
import com.informatorio.tpJavaFinal.entity.Tag;
import com.informatorio.tpJavaFinal.entity.Usuario;
import com.informatorio.tpJavaFinal.repository.EmprendimientoRepository;
import com.informatorio.tpJavaFinal.repository.TagRepository;
import com.informatorio.tpJavaFinal.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprendimientoService {

    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TagRepository tagRepository;

    @Autowired
    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository, UsuarioRepository usuarioRepository,
        TagRepository tagRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.tagRepository = tagRepository; 
    }

    public Emprendimiento createEmprendimiento(OperacionEmprendimiento operacionEmprendimiento) {

        Optional<Usuario> usuario = usuarioRepository.findById(operacionEmprendimiento.getIdUsuario())/* 
                .orElseThrow(() -> new EntityNotFoundException("Usuario No Encontrado")) ;

        List<Tag> tags = tagRepository.findAllById(operacionEmprendimiento.getTags());

        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setNombre(operacionEmprendimiento.getNombre());
        emprendimiento.setDescripcion(operacionEmprendimiento.getDescripcion());
        emprendimiento.setJefe(usuario);
        emprendimiento.getTags().addAll(tags);

        return emprendimientoRepository.save(emprendimiento);
    }

    public ArrayList<Emprendimiento> obtenerEmprendimientos(){
        return (ArrayList<Emprendimiento>) emprendimientoRepository.findAll();
    }
 */





    /* @Autowired
    EmprendimientoRepository emprendimientoRepository;


    public ArrayList<Emprendimiento> obtenerEmprendimientos(){
        return (ArrayList<Emprendimiento>) emprendimientoRepository.findAll();
    }


    public Emprendimiento guardarEmprendimiento(Emprendimiento emprendimientoNuevo){
        if (emprendimientoNuevo.getId() == null){
            return emprendimientoRepository.save(emprendimientoNuevo);
            
        }else{
            emprendimientoNuevo.setFechaAlta(obtenerPorId(emprendimientoNuevo.getId()).get().getFechaAlta());

            return emprendimientoRepository.save(emprendimientoNuevo);
        }        
    }

    public Optional<Emprendimiento> obtenerPorId(Long id){
        //utilizamos una clase "optional" para el caso en que id no exista, no se rompa el programa.
        return emprendimientoRepository.findById(id);
    }

    public ArrayList<Emprendimiento> obtenerPorCategoria(String categoria){
        return emprendimientoRepository.findByCategoria(categoria);
    }

    public boolean eliminarEmprendimiento(Long id){
        try {
            emprendimientoRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    } */
