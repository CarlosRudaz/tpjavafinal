package com.informatorio.tpJavaFinal.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import com.informatorio.tpJavaFinal.dto.OperacionEmprendimiento;
import com.informatorio.tpJavaFinal.entity.Emprendimiento;
import com.informatorio.tpJavaFinal.service.EmprendimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprendimiento")
public class EmprendimientoController {
    
    private final EmprendimientoService emprendimientoService;

    

    @Autowired
    public EmprendimientoController(EmprendimientoService emprendimientoService) {
        this.emprendimientoService = emprendimientoService;
    }

    @PostMapping()
    public ResponseEntity<?> createEmprendimiento(@Valid @RequestBody OperacionEmprendimiento operacionEmprendimiento) {
        return new ResponseEntity<>(emprendimientoService.createEmprendimiento(operacionEmprendimiento), HttpStatus.CREATED);
    }

    @GetMapping()
    public ArrayList<Emprendimiento> obtenerEmprendimientos(){
        return emprendimientoService.obtenerEmprendimientos();
    }

    // @PostMapping()
    // public Emprendimiento guardarUsuario(@RequestBody Emprendimiento emprendimiento){
    //     return this.emprendimientoService.guardarEmprendimiento(emprendimiento);
    // }

    // @GetMapping( path = "/{categoria}")
    // public ArrayList<Emprendimiento> obtenerUsuarioPorCategoria(@PathVariable("categoria") String categoria){
    //     return this.emprendimientoService.obtenerPorCategoria(categoria);
    // }
}
