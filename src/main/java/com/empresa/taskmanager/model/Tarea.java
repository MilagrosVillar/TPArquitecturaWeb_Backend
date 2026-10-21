package com.empresa.taskmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 180)
    @Column(nullable = false, length = 180)
    private String titulo;

    @NotBlank
    @Column(nullable = false, columnDefinition = "text")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Prioridad prioridad;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoTarea estado = EstadoTarea.PENDIENTE;

    @Column(nullable = false, updatable = false)
    private Instant fechaCreacion;

    @Column(nullable = false)
    private Instant fechaActualizacion;

    public Tarea() {
    }

    @PrePersist
    void alCrear() {
        Instant ahora = Instant.now();
        fechaCreacion = ahora;
        fechaActualizacion = ahora;
    }

    @PreUpdate
    void alActualizar() {
        fechaActualizacion = Instant.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public EstadoTarea getEstado() { return estado; }
    public void setEstado(EstadoTarea estado) { this.estado = estado; }
    public Instant getFechaCreacion() { return fechaCreacion; }
    public Instant getFechaActualizacion() { return fechaActualizacion; }
}
