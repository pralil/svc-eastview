package org.pralil.eastview.svc.eastview.svceastview.services;

import org.pralil.eastview.svc.eastview.svceastview.models.entity.Ciudadano;
import org.pralil.eastview.svc.eastview.svceastview.repositories.CiudadanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadanoServiceImpl implements CiudadanoService{

    @Autowired
    private CiudadanoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Ciudadano> listar() {
        return (List<Ciudadano>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ciudadano> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Ciudadano guardar(Ciudadano ciudadano) {
        return repository.save(ciudadano);
    }

    @Override
    @Transactional
    public void eliminar(long id) {
        repository.deleteById(id);
    }
}
