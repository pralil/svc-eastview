package org.pralil.eastview.svc.eastview.svceastview.controllers;

import org.pralil.eastview.svc.eastview.svceastview.models.entity.Ciudadano;
import org.pralil.eastview.svc.eastview.svceastview.models.entity.Tarea;
import org.pralil.eastview.svc.eastview.svceastview.models.entity.TareasPorCiudadanos;
import org.pralil.eastview.svc.eastview.svceastview.services.CiudadanoService;
import org.pralil.eastview.svc.eastview.svceastview.services.TareaService;
import org.pralil.eastview.svc.eastview.svceastview.services.TareasPorCiudadanoService;
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
public class TareasPorCiudadanoController {

    @Autowired
    private TareasPorCiudadanoService tareasPorCiudadanoService;

    @Autowired
    private CiudadanoService ciudadanoService;

    @Autowired
    private TareaService tareaService;

    @GetMapping("/tareas-ciudadanos")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(tareasPorCiudadanoService.listar());
    }

    @GetMapping("/tareas-ciudadanos/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<TareasPorCiudadanos> o = tareasPorCiudadanoService.porId(id);
        if( o.isPresent() ){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/tareas-ciudadanos")
    public ResponseEntity<?> crear(@Valid @RequestBody TareasPorCiudadanos tareasPorCiudadanos, BindingResult result){
        if( result.hasErrors() ) {
            return validar(result);
        }
        TareasPorCiudadanos tareasPorCiudadanosDb = tareasPorCiudadanoService.guardar(tareasPorCiudadanos);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareasPorCiudadanosDb);
    }

    @PutMapping("/{tareasPorCiudadanosId}/ciudadanos/{ciudadanoId}")
    public TareasPorCiudadanos asignarCiudadanoATarea(
            @PathVariable Long tareasPorCiudadanosId,
            @PathVariable Long ciudadanoId
    ) {
        TareasPorCiudadanos tareasPorCiudadanos = tareasPorCiudadanoService.porId(tareasPorCiudadanosId).get();
        Ciudadano ciudadano = ciudadanoService.porId(ciudadanoId).get();
        tareasPorCiudadanos.asignarTarea(ciudadano);
        return tareasPorCiudadanoService.guardar(tareasPorCiudadanos);
    }

    @PutMapping("/{tareasPorCiudadanosId}/tareas/{tareaId}")
    public TareasPorCiudadanos asignarTareaATareaCiudadano(
            @PathVariable Long tareasPorCiudadanosId,
            @PathVariable Long tareaId
    ) {
        TareasPorCiudadanos tareasPorCiudadanos = tareasPorCiudadanoService.porId(tareasPorCiudadanosId).get();
        Tarea tarea = tareaService.porId(tareaId).get();
        tareasPorCiudadanos.asignarTareaCiudadano(tarea);
        return tareasPorCiudadanoService.guardar(tareasPorCiudadanos);
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo "+ err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
