package com.informatorio.tpJavaFinal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede ser vacio")
    private String nombre;

    @NotEmpty(message = "El apellido no puede ser vacio")
    private String apellido;

    @NotEmpty(message = "El nombre de la ciudad no puede ser vacio")
    private String ciudad;

    @NotEmpty(message = "El apellido no puede ser vacio")
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "El password no puede ser vacio")
    @Size(min = 8, max = 20)
    private String password;

    private String provincia;

    private String pais;



    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprendimiento> emprendimientos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void agregarCarrito(Emprendimiento emprendimiento) {
        emprendimientos.add(emprendimiento);
        emprendimiento.setOwner(this);
    }

    public void removerCarrito(Emprendimiento emprendimiento) {
        emprendimientos.remove(emprendimiento);
        emprendimiento.setOwner(null);
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
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

}









/* import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity  //informa que este código es un modelo
@Table(name = "usuario")
public class Usuario {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;


    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String email;

    
    private String password;
    private String ciudad;
    private String provincia;
    private String pais;
    
    private Integer tipo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaAlta;

    //CONSTRUCTORES
    public Usuario(String nombre, String apellido, String email, String password, String ciudad, String provincia,
            String pais, Integer tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
        this.tipo = tipo;
    }

    public Usuario() {
    }

    //GETTERS AND SETTERS
    public long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getApellido() {
        return this.apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
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
        return TipoUsuario.getType(this.tipo);
    }


    public void setTipo(TipoUsuario tipo) {
        if(tipo == null){
            this.tipo = null;
        }else{
            this.tipo = tipo.getId();
        }
    }
 

    public Calendar getFechaAlta() {
        return this.fechaAlta;
    }

    public void setFechaAlta(Calendar fechaAlta){
        this.fechaAlta = fechaAlta;
    }


    @Override
    public String toString() {
        return "UsuarioModel [apellido=" + apellido + ", fechaAlta=" + fechaAlta + ", id=" + id + ", nombre=" + nombre
                + ", password=" + password + ", tipo=" + tipo + "]";
    }


} */