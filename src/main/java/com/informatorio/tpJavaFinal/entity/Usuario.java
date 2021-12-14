package com.informatorio.tpJavaFinal.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "nombre no deberia estar vacio")
    private String nombre;

    @NotEmpty(message = "apellido no deberia estar vacio")
    private String apellido;

    @NotEmpty(message = "el email debe ser valido")
    @Column(unique = true)
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String username;

    @NotEmpty(message = "password no deberia estar vacio")
    private String password;
    
    private Boolean activo = true;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    @NotEmpty(message = "ciudad no deberia estar vacio")
    private String ciudad;

    @NotEmpty(message = "provincia no deberia estar vacio")
    private String provincia;

    @NotEmpty(message = "pais no deberia estar vacio")
    private String pais;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TipoUsuario tipo;

    @OneToMany(mappedBy = "jefe", cascade = CascadeType.ALL)
    private List<Emprendimiento> emprendimiento = new ArrayList<Emprendimiento>();
    
    public Long getId() {
        return this.id;
    }
    
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.username;
    }

    public void setEmail(String email) {
        this.username = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isActivo() {
        return this.activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaDeCreacion() {
        return this.fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    
    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public TipoUsuario getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public List<Emprendimiento> getEmprendimiento() {
        return this.emprendimiento;
    }

    /* public void setEmprendimientos(List<Emprendimiento> emprendimientos) {
        this.emprendimiento = emprendimientos;
    }

    public void addEmprendimiento(Emprendimiento emprendimiento) {
        emprendimiento.add(emprendimiento);
        emprendimiento.setCreador(this);
    }

    public void removeEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientos.remove(emprendimiento);
        emprendimiento.setCreador(null);
    } */

}