package com.informatorio.tpJavaFinal.controller;


import javax.validation.Valid;

import com.informatorio.tpJavaFinal.dto.EventoDto;
import com.informatorio.tpJavaFinal.entity.Emprendimiento;
import com.informatorio.tpJavaFinal.repository.EmprendimientoRepository;
import com.informatorio.tpJavaFinal.repository.EventoRepository;
import com.informatorio.tpJavaFinal.service.EmprendimientoService;
import com.informatorio.tpJavaFinal.service.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmprendimientoController {

    private final EmprendimientoRepository emprendimientoRepository;
    private final EmprendimientoService emprendimientoService;
    private final EventoRepository eventoRepository;
    private final EventoService eventoService;

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository, EmprendimientoService emprendimientoService,
                                    EventoRepository eventoRepository, EventoService eventoService) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.emprendimientoService = emprendimientoService;
        this.eventoRepository = eventoRepository;
        this.eventoService = eventoService;
    }

    @PostMapping(value = "/usuarios/{id}/emprendimiento")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("id") Long usuarioId,
                                                 @Valid @RequestBody Emprendimiento emprendimiento) {
        return new ResponseEntity<>(emprendimientoService.guardarEmprendimiento(usuarioId, emprendimiento), HttpStatus.CREATED);                                          
    }
    @PutMapping(value = "/emprendimiento/{id}/quitar")
    public Emprendimiento eliminarEmprendimiento(@PathVariable("id") Long id, Emprendimiento emprendimiento) {
        return this.emprendimientoService.eliminarEmprendimiento(id, emprendimiento);
    }
    /* @PutMapping(value = "/emprendimiento/{id}")
    public Emprendimiento modificarEmprendimiento(@PathVariable("id") Long id,
                                                  @Valid @RequestBody Emprendimiento emprendimiento) {
        return this.emprendimientoService.modificar(id, emprendimiento);
    } */
    @GetMapping(value = "/emprendimiento")
    public ResponseEntity<?> obtenerTodosLosEmprendimientos(
            @RequestParam(name = "nombre", required = false) String nombre) {
        return new ResponseEntity<>(emprendimientoService.obtenerTodosLosEmprendimientos(nombre) ,HttpStatus.OK);
    }
    @GetMapping(value = "/emprendimiento/no_publicados")
    public ResponseEntity<?> obtenerEmprendimientosNoPublicados() {
        return new ResponseEntity<>(emprendimientoService.obtenerEmprendimientosNoPublicados(), HttpStatus.OK);
    }
    @PostMapping(value = "/emprendimiento/{emprendimientoId}/eventos/{eventoId}")
    public ResponseEntity<?> registrarEvento(@PathVariable("emprendimientoId") Long emprendimientoId,
                                             @PathVariable("eventoId") Long eventoId, EventoDto eventoDTO) {
        emprendimientoRepository.findById(emprendimientoId);
        eventoRepository.findById(eventoId);
        return new ResponseEntity<>(eventoService.registrar(emprendimientoId, eventoId, eventoDTO), HttpStatus.CREATED);                                     
    }









    /* @PostMapping()
    public ResponseEntity<?> createEmprendimiento(@Valid 
                                        @RequestBody OperacionEmprendimiento operacionEmprendimiento) {
        return new ResponseEntity<>(emprendimientoService.createEmprendimiento(operacionEmprendimiento), HttpStatus.CREATED);
    }

    @GetMapping()
    public ArrayList<Emprendimiento> obtenerEmprendimientos(){
        return emprendimientoService.obtenerEmprendimientos();
    } */

    // @PostMapping()
    // public Emprendimiento guardarUsuario(@RequestBody Emprendimiento emprendimiento){
    //     return this.emprendimientoService.guardarEmprendimiento(emprendimiento);
    // }

    // @GetMapping( path = "/{categoria}")
    // public ArrayList<Emprendimiento> obtenerUsuarioPorCategoria(@PathVariable("categoria") String categoria){
    //     return this.emprendimientoService.obtenerPorCategoria(categoria);
    // }
}
