package com.informatorio.tpJavaFinal.service;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

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

@Service
public class EmprendimientoService {

    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TagRepository tagRepository;

    @Autowired
    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository,
                                 UsuarioRepository usuarioRepository,
                                 TagRepository tagRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.tagRepository = tagRepository; 
    }

    public Emprendimiento createEmprendimiento(OperacionEmprendimiento operacionEmprendimiento) {
        Usuario usuario = usuarioRepository.findById(operacionEmprendimiento.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario No Encontrado"));
        List<Tag> tags = tagRepository.findAllById(operacionEmprendimiento.getTags());
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setNombre(operacionEmprendimiento.getNombre());
        emprendimiento.setDescripcion(operacionEmprendimiento.getDescripcion());
        emprendimiento.setOwner(usuario);
        emprendimiento.getTags().addAll(tags);

        return emprendimientoRepository.save(emprendimiento);
    }

    public ArrayList<Emprendimiento> obtenerEmprendimientos(){
        return (ArrayList<Emprendimiento>) emprendimientoRepository.findAll();
    }






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

}
