package com.salesianostriana.dam.tarea1612.services;

import com.salesianostriana.dam.tarea1612.model.Course;
import com.salesianostriana.dam.tarea1612.model.Review;
import com.salesianostriana.dam.tarea1612.model.User;
import com.salesianostriana.dam.tarea1612.repository.CourseRepository;
import com.salesianostriana.dam.tarea1612.repository.ReviewRepository;
import com.salesianostriana.dam.tarea1612.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private CourseRepository courseRepository;

    public Review addReview(Long userId, Long courseId, String comment, Integer rating){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(("Usuario no encontrado")));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException(("Curso no encontrado")));

        Review review = new Review();
        review.setUser(user);
        review.setCourse(course);
        review.setComment(comment);
        review.setRating(rating);

        return reviewRepository.save(review);
    }
}
