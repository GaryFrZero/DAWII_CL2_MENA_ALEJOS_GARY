package com.cibertec.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idesp;

    @Column(name = "nomesp")
    private String nomesp;


    @JsonManagedReference
    @OneToMany(mappedBy = "especialidad",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alumno> listaAlumno = new ArrayList<>();
}