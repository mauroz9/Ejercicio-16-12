package com.salesianostriana.dam.tarea1612.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;


    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    private CourseStatus courseStatus;

    @ManyToMany
    @JoinTable(
            name = "course_category",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    public void addLesson(Lesson lesson) {
       if(this.courseStatus.equals(CourseStatus.PUBLISHED)){
           lessons.add(lesson);
           lesson.setCourse(this);
       }else{
           throw new IllegalArgumentException("No se puede añadir lecciones a un curso archivado");
       }
    }

    public boolean hasInstructor(){
        return this.instructor != null && this.instructor.getRol().equals(Rol.TEACHER);
    }

    public boolean hasLessons(){
        return !this.lessons.isEmpty();
    }




}
