package com.robert.projects.MovieManagement.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.robert.projects.MovieManagement.persistence.entity.User;

public interface UserCrudRepository extends JpaRepository<User, Long> {
  @EntityGraph(value = "User.ratings")
  List<User> findAll();

  @EntityGraph(value = "User.ratings")
  List<User>  findByNameContaining(String name);
  Optional<User> findByUsername(String username);
  
  @Modifying // indiciar que no es operación de lectura
  int deleteByUsername(String username);
}
