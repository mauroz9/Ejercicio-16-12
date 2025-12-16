package com.salesianostriana.dam.tarea1612.repository;

import com.salesianostriana.dam.tarea1612.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInterface extends JpaRepository<Course, Long> {
}
