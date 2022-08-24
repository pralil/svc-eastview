package org.pralil.eastview.svc.eastview.svceastview.controllers;

import org.pralil.eastview.svc.eastview.svceastview.models.entity.Tarea;
import org.pralil.eastview.svc.eastview.svceastview.services.TareaService;
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
public class TareasController {

    @Autowired
    private TareaService service;

    @GetMapping("/tareas")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/tareas/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Tarea> o = service.porId(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/tareas")
    public ResponseEntity<?> crear(@Valid @RequestBody Tarea tarea, BindingResult result){
        if(result.hasErrors()){
            return validar(result);
        }
        Tarea tareaDb = service.guardar(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaDb);
    }

    @PutMapping("/tareas/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Tarea tarea, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<Tarea> o = service.porId(id);
        if(o.isPresent()){
            Tarea tareaDb = o.get();
            tareaDb.setTarea(tarea.getTarea());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(tareaDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/tareas/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Tarea> o = service.porId(id);
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
