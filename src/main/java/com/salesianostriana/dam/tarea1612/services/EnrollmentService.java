package com.salesianostriana.dam.tarea1612.services;

import com.salesianostriana.dam.tarea1612.model.Course;
import com.salesianostriana.dam.tarea1612.model.Enrollment;
import com.salesianostriana.dam.tarea1612.model.EnrollmentStatus;
import com.salesianostriana.dam.tarea1612.model.User;
import com.salesianostriana.dam.tarea1612.repository.CourseRepository;
import com.salesianostriana.dam.tarea1612.repository.EnrrollmentRepository;
import com.salesianostriana.dam.tarea1612.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private EnrrollmentRepository enrrollmentRepository;
    private UserRepository userRepository;
    private CourseRepository courseRepository;

    public Enrollment enrollUser(Long studentId, Long courseId){
        User student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        if(enrrollmentRepository.findByStudentAndCourse(student, course).isPresent()){
            throw new RuntimeException("El estudiante ya está matriculado");

        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.ENROLLED);
        enrollment.setProgressPercent(0);

        return enrrollmentRepository.save(enrollment);
    }

    public Enrollment updateProgress(Long enrollmentId, Integer newProgress) {
        Enrollment enrollment = enrrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));

        if (newProgress < 0 || newProgress > 100) {
            throw new IllegalArgumentException("El progreso debe estar entre 0 y 100");
        }

        enrollment.setProgressPercent(newProgress);

        if (newProgress == 100) {
            enrollment.setStatus(EnrollmentStatus.COMPLETED);
        }

        return enrrollmentRepository.save(enrollment);
    }

}
