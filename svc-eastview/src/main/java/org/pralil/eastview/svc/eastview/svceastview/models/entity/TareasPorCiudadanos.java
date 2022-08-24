package org.pralil.eastview.svc.eastview.svceastview.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tareas_ciudadanos")
public class TareasPorCiudadanos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name="ciudadano_asignado",
            joinColumns = @JoinColumn(name = "tarea_ciudadano_id"),
            inverseJoinColumns = @JoinColumn(name = "ciudadano_id")
    )
    private Set<Ciudadano> asignarTareaACiudadano = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tarea_id", referencedColumnName = "id")
    private Tarea tarea;

    @NotBlank
    private String dia;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }


    public Set<Ciudadano> getAsignarTareaACiudadano() {
        return asignarTareaACiudadano;
    }

    public void asignarTarea(Ciudadano ciudadano) {
        asignarTareaACiudadano.add(ciudadano);
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void asignarTareaCiudadano(Tarea tarea) {
        this.tarea = tarea;
    }
}
