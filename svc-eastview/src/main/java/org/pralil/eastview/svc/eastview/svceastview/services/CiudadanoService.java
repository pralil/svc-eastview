package org.pralil.eastview.svc.eastview.svceastview.services;

import org.pralil.eastview.svc.eastview.svceastview.models.entity.Ciudadano;

import java.util.List;
import java.util.Optional;

public interface CiudadanoService {

    List<Ciudadano> listar();
    Optional<Ciudadano> porId(Long id);
    Ciudadano guardar(Ciudadano ciudadano);
    void eliminar(long id);


}
