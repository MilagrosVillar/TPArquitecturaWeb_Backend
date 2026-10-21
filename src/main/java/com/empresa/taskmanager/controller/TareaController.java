package com.empresa.taskmanager.controller;

import com.empresa.taskmanager.model.Tarea;
import com.empresa.taskmanager.service.interfaces.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> listar() {
        return ResponseEntity.ok(tareaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tareaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Tarea> crear(@Valid @RequestBody Tarea tarea) {
        Tarea creada = tareaService.crear(tarea);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.getId())
                .toUri();
        return ResponseEntity.created(ubicacion).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.actualizar(id, tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
