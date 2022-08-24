package org.pralil.eastview.svc.eastview.svceastview.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ciudadanos")
public class Ciudadano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "asignarTareaACiudadano")
    private Set<TareasPorCiudadanos> tareasPorCiudadanos = new HashSet<>();

    @NotBlank
    private String nombre;

    @NotBlank
    private String direccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<TareasPorCiudadanos> getTareasPorCiudadanos() {
        return tareasPorCiudadanos;
    }
}
