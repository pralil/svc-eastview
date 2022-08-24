package org.pralil.eastview.svc.eastview.svceastview.controllers;

import org.pralil.eastview.svc.eastview.svceastview.models.entity.Ciudadano;
import org.pralil.eastview.svc.eastview.svceastview.services.CiudadanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CiudadanoController {

    @Autowired
    private CiudadanoService service;

    @GetMapping("/ciudadanos")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/ciudadanos/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Ciudadano> o = service.porId(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/ciudadanos")
    public ResponseEntity<?> crear(@Valid @RequestBody Ciudadano ciudadano, BindingResult result){
        if( result.hasErrors() ){
            return validar(result);
        }
        Ciudadano ciudadanoDb = service.guardar(ciudadano);
        return ResponseEntity.status(HttpStatus.CREATED).body(ciudadanoDb);
    }

    @PutMapping("/ciudadanos/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Ciudadano ciudadano, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<Ciudadano> o = service.porId(id);
        if( o.isPresent() ){
            Ciudadano ciudadanoDb = o.get();
            ciudadanoDb.setNombre(ciudadano.getNombre());
            ciudadanoDb.setDireccion(ciudadano.getDireccion());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(ciudadanoDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/ciudadanos/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Ciudadano> o = service.porId(id);
        if(o.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }


    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo "+ err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }



}
