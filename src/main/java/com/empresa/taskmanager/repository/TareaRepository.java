package com.empresa.taskmanager.repository;

import com.empresa.taskmanager.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
