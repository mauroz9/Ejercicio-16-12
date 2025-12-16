package com.salesianostriana.dam.tarea1612.services;

import com.salesianostriana.dam.tarea1612.model.Category;
import com.salesianostriana.dam.tarea1612.model.Course;
import com.salesianostriana.dam.tarea1612.model.Lesson;
import com.salesianostriana.dam.tarea1612.model.User;
import com.salesianostriana.dam.tarea1612.repository.CategoryRepository;
import com.salesianostriana.dam.tarea1612.repository.CourseRepository;
import com.salesianostriana.dam.tarea1612.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    public Course createCourseWithLessons(Long instructorId, String title, List<Lesson> lessons, List<Long> categoryIds){
        User instructor = userRepository.findById(instructorId).orElseThrow(() -> new RuntimeException(("Instructor no encontrado")));

        Course course = new Course();
        course.setTitle(title);
        course.setInstructor(instructor);

        if (lessons != null) {
            lessons.forEach(course::addLesson);
        }

        List<Category> categories = categoryRepository.findAllById(categoryIds);
        course.setCategories(categories);

        return courseRepository.save(course);
    }
}
