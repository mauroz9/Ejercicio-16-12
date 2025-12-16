package com.salesianostriana.dam.tarea1612.repository;

import com.salesianostriana.dam.tarea1612.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
