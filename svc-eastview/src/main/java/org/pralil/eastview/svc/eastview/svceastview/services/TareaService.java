package org.pralil.eastview.svc.eastview.svceastview.services;

import org.pralil.eastview.svc.eastview.svceastview.models.entity.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaService {

    List<Tarea> listar();
    Optional<Tarea> porId(Long id);
    Tarea guardar(Tarea tarea);
    void eliminar(long id);
}
