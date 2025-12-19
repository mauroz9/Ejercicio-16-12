package com.salesianostriana.dam.tarea1612.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Rol rol;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    public void hasThatLesson(Lesson lesson){
        for (Course c: courses){
            for (Lesson l: c.getLessons()){
                if(l.getTitle().equalsIgnoreCase(lesson.getTitle())){
                    throw new RuntimeException("No puedes asignar la asignatura " + lesson.getTitle() + "porque el profesor tiene una asignada con el mismo título");
                }
            }
        }
    }
}
