package com.informatorio.tpJavaFinal.entity;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    private Usuario owner;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "empredimiento_id",
            joinColumns = @JoinColumn(name = "emprendimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    private ArrayList<String> url;

    private String descripcion;

    public Long getId() {
        return id;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(ArrayList<String> url) {
        this.url = url;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    public float getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(float objetivo) {
        this.objetivo = objetivo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
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
        return tags;
    }
}






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