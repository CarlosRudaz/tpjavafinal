package com.informatorio.tpJavaFinal.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class OperacionEmprendimiento {

    @NotEmpty(message = "El nombre no puede ser vacío")
    private String nombre;

    @NotEmpty(message = "La descripcion no puede estar vacía")
    private String descripcion;

    @NotNull
    @Positive
    private Long idUsuario;

    private List<Long> tags;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }
}