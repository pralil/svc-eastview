package org.pralil.eastview.svc.eastview.svceastview.services;

import org.pralil.eastview.svc.eastview.svceastview.models.entity.TareasPorCiudadanos;

import java.util.List;
import java.util.Optional;

public interface TareasPorCiudadanoService {

    List<TareasPorCiudadanos> listar();
    Optional<TareasPorCiudadanos> porId(Long id);
    TareasPorCiudadanos guardar(TareasPorCiudadanos tareasPorCiudadanos);
    void eliminar(long id);
}
