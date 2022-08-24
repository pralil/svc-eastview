package org.pralil.eastview.svc.eastview.svceastview.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "tarea")
    private Set<TareasPorCiudadanos> tareasPorCiudadanos = new HashSet<>();

    @NotBlank
    private String tarea;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Set<TareasPorCiudadanos> getTareasPorCiudadanos() {
        return tareasPorCiudadanos;
    }
}
