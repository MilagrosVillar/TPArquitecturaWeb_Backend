package com.empresa.taskmanager.service.impl;

import com.empresa.taskmanager.exception.ResourceNotFoundException;
import com.empresa.taskmanager.model.EstadoTarea;
import com.empresa.taskmanager.model.Tarea;
import com.empresa.taskmanager.repository.TareaRepository;
import com.empresa.taskmanager.service.interfaces.TareaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> listar() {
        return tareaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tarea buscarPorId(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la tarea con id " + id));
    }

    @Override
    public Tarea crear(Tarea tarea) {
        tarea.setId(null);
        tarea.setEstado(EstadoTarea.PENDIENTE);
        return tareaRepository.save(tarea);
    }

    @Override
    public Tarea actualizar(Long id, Tarea datos) {
        Tarea existente = buscarPorId(id);
        existente.setTitulo(datos.getTitulo());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrioridad(datos.getPrioridad());
        return tareaRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        Tarea existente = buscarPorId(id);
        tareaRepository.delete(existente);
    }
}
