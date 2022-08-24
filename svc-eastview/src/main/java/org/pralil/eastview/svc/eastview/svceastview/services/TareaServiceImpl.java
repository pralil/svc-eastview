package org.pralil.eastview.svc.eastview.svceastview.services;

import org.pralil.eastview.svc.eastview.svceastview.models.entity.Tarea;
import org.pralil.eastview.svc.eastview.svceastview.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService{

    @Autowired
    private TareaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> listar() {
        return (List<Tarea>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tarea> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Tarea guardar(Tarea tarea) {
        return repository.save(tarea);
    }

    @Override
    @Transactional
    public void eliminar(long id) {
        repository.deleteById(id);
    }
}
