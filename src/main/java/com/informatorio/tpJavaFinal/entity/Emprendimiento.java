package com.informatorio.tpJavaFinal.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;



@Entity
@Where(clause = "activo = true")
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "nombre no deberia estar vacio")
    private String nombre;

    @NotEmpty(message = "descripcion no deberia estar vacio")
    private String descripcion;

    @NotEmpty(message = "contenido no deberia estar vacio")
    private String contenido;

    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    private LocalDateTime ultimaModificacion;

    @NotNull
    @Positive
    private Double objetivo;

    private Boolean publicado;

    @Column(insertable = false)
    private Boolean activo = true;

    @NotNull
    private String url;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<Tag>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario jefe;
    
    @OneToMany(mappedBy = "emprendimientoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos = new ArrayList<Voto>();

    private Integer contadorDeVotos = 0;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Evento> eventos;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public LocalDateTime getUltimaModificacion() {
        return ultimaModificacion;
    }
    public void setUltimaModificacion(LocalDateTime ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }
    public Double getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(Double objetivo) {
        this.objetivo = objetivo;
    }
    public Boolean isPublicado() {
        return publicado;
    }
    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }
    public Boolean isActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public void addTags(Tag tag) {
        tags.add(tag);
        tag.getEmprendimientos().add(this);
    }
    public String getjefe() {
        return jefe.getEmail();
    }
    public void setJefe(Usuario jefe) {
        this.jefe = jefe;
    }
    public List<Voto> getVotos() {
        return votos;
    }
    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
    public Integer getContadorDeVotos() {
        return contadorDeVotos;
    }
    public void setContadorDeVotos(Integer contadorDeVotos) {
        this.contadorDeVotos = contadorDeVotos;
    }
    public List<Evento> getEventos() {
        return eventos;
    }
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    public void addEvento(Evento evento) {
        if (this.eventos == null) {
            this.eventos = new ArrayList<>();
        }
        this.eventos.add(evento);
    }   
}
/* import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Entity
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede ser vacio")
    private String nombre;

    private String contenido;
    private String categoria;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaAlta;

    private float objetivo;
    private Boolean publicado;

    

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario jefe;

    /* @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "empredimiento_id",
            joinColumns = @JoinColumn(name = "emprendimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>(); 

    @NotNull
    private String url;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<Tag>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario creador;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "emprendimientoId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos = new ArrayList<Voto>();
    private Integer contadorDeVotos = 0;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Evento> eventos;

    
    private String descripcion;

    public Long getId() {
        return this.id;
    }

    public List<String> getUrl() {
        return this.url;
    }

    public void setUrl(ArrayList<String> url) {
        this.url = url;
    }

    public Boolean getPublicado() {
        return this.publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    public float getObjetivo() {
        return this.objetivo;
    }

    public void setObjetivo(float objetivo) {
        this.objetivo = objetivo;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getContenido() {
        return this.contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getJefe() {
        return this.jefe;
    }

    public void setJefe(Optional<Usuario> usuario) {
        this.jefe = usuario.get();
    }

    public void agregarTag(Tag tag) {
        tags.add(tag);
        tag.getEmprendimientos().add(this);
    }

    public void removerTag(Tag tag) {
        tags.remove(tag);
        tag.getEmprendimientos().remove(null);
    }

    public List<Tag> getTags() {
        return this.tags;
    }
}
 */





/* import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class Emprendimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    //@NotEmpty(message = "El nombre no puede ser vacío")
    private String nombre;

    @Column(nullable = false)
    private String descripcion;
    private String contenido;
    private String categoria;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaAlta;

    private float objetivo;
    private Boolean publicado;

    private ArrayList<String> url;
    private ArrayList<String> tags;


    
    
    public Emprendimiento(String nombre,String categoria, String descripcion, String contenido, float objetivo, Boolean publicado,
            String url, String tags) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.objetivo = objetivo;
        this.publicado = publicado;
        this.url.add(url);
        this.tags.add(tags);
        
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getUrl() {
        return url;
    }

    public void setUrl(ArrayList<String> url) {
        this.url = url;
    }

    public Emprendimiento(){}

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return this.contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Calendar getFechaAlta() {
        return this.fechaAlta;
    }
    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public float getObjetivo() {
        return this.objetivo;
    }

    public void setObjetivo(float objetivo) {
        this.objetivo = objetivo;
    }

    public Boolean getPublicado() {
        return this.publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    
}
 */