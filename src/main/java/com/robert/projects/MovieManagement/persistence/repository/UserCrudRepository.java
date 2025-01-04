package com.robert.projects.MovieManagement.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.robert.projects.MovieManagement.persistence.entity.User;

public interface UserCrudRepository extends JpaRepository<User, Long> {
  List<User>  findByNameContaining(String name);
  Optional<User> findByUsername(String username);
  
  @Modifying // indiciar que no es operación de lectura
  void deleteByUsername();
}
