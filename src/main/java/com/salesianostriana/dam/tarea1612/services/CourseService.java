package com.salesianostriana.dam.tarea1612.services;

import com.salesianostriana.dam.tarea1612.model.*;
import com.salesianostriana.dam.tarea1612.repository.CategoryRepository;
import com.salesianostriana.dam.tarea1612.repository.CourseRepository;
import com.salesianostriana.dam.tarea1612.repository.EnrrollmentRepository;
import com.salesianostriana.dam.tarea1612.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private EnrrollmentRepository enrrollmentRepository;

    public Course createCourseWithLessons(Long instructorId, String title, List<Lesson> lessons, List<Long> categoryIds){
        User instructor = userRepository.findById(instructorId).orElseThrow(() -> new RuntimeException(("Instructor no encontrado")));

        if(!instructor.getRol().equals(Rol.TEACHER)){
            throw new RuntimeException("No puedes tomar un alumno como profesor");
        }



        Course course = new Course();
        course.setTitle(title);
        course.setInstructor(instructor);



        if (lessons != null) {
            lessons.forEach(instructor::hasThatLesson);
            lessons.forEach(course::addLesson);
        }

        if(!course.hasLessons()){
            throw new IllegalArgumentException("No puedes crear un curso sin lecciones");
        }

        if(!course.hasInstructor()){
            throw new IllegalArgumentException("No puedes crear un curso sin un instructor");
        }

        List<Category> categories = categoryRepository.findAllById(categoryIds);
        course.setCategories(categories);

        course.setCourseStatus(CourseStatus.ARCHIVED);

        return courseRepository.save(course);
    }

    public Course publishCourse(Long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el curso"));

        course.setCourseStatus(CourseStatus.PUBLISHED);

        return courseRepository.save(course);
    }
}
