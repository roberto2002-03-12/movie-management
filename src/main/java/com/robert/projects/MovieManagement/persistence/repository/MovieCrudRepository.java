package com.robert.projects.MovieManagement.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.robert.projects.MovieManagement.persistence.entity.Movie;

public interface MovieCrudRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
  @EntityGraph(value = "Movie.ratings")
  List<Movie> findAll();

  @EntityGraph(attributePaths = {"ratings"})
  Page<Movie> findAll(Specification<Movie> spec, Pageable pageable);
}
