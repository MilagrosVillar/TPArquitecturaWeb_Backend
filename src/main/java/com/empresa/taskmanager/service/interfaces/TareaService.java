package com.empresa.taskmanager.service.interfaces;

import com.empresa.taskmanager.model.Tarea;

import java.util.List;

public interface TareaService {
    List<Tarea> listar();
    Tarea buscarPorId(Long id);
    Tarea crear(Tarea tarea);
    Tarea actualizar(Long id, Tarea datos);
    void eliminar(Long id);
}
