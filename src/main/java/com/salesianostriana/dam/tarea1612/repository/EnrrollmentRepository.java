package com.salesianostriana.dam.tarea1612.repository;

import com.salesianostriana.dam.tarea1612.model.Course;
import com.salesianostriana.dam.tarea1612.model.Enrollment;
import com.salesianostriana.dam.tarea1612.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByStudentAndCourse(User student, Course course);
}
