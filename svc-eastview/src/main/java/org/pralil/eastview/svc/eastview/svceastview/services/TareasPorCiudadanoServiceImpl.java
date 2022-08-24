package org.pralil.eastview.svc.eastview.svceastview.services;

import org.pralil.eastview.svc.eastview.svceastview.models.entity.TareasPorCiudadanos;
import org.pralil.eastview.svc.eastview.svceastview.repositories.TareasPorCiudadanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TareasPorCiudadanoServiceImpl implements TareasPorCiudadanoService{

    @Autowired
    private TareasPorCiudadanoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<TareasPorCiudadanos> listar() {
        return (List<TareasPorCiudadanos>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TareasPorCiudadanos> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public TareasPorCiudadanos guardar(TareasPorCiudadanos tareasPorCiudadanos) {
        return repository.save(tareasPorCiudadanos);
    }

    @Override
    public void eliminar(long id) {
        repository.deleteById(id);
    }
}
