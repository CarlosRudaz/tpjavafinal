package com.informatorio.tpJavaFinal.controller;

import javax.validation.Valid;

import com.informatorio.tpJavaFinal.entity.Evento;
import com.informatorio.tpJavaFinal.service.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/evento")
public class EventoController {
    private final EventoService eventoService;
    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<?> crearEvento(@Valid @RequestBody Evento evento) {
        return new ResponseEntity<>(eventoService.guardarEvento(evento), HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}/eliminar")
    public Evento eliminarEvento(@PathVariable("id") Long id, Evento evento) {
        return this.eventoService.eliminarEvento(id, evento);
    }
    @PutMapping(value = "/actualizar-estado")
    public void actualizarEvento() { this.eventoService.actualizarEvento(); }
    @GetMapping(value = "/{id}/ranking")
    public ResponseEntity<?> rankingDelEvento(@PathVariable("id") Long id) {
        return new ResponseEntity<>(eventoService.rankear(id), HttpStatus.OK);
    }
}