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
  // Containing es como el like de SQL

  // consulta sin query
  @EntityGraph(value = "Movie.ratings")
  List<Movie> findAll();

  // consulta con query
  @EntityGraph(attributePaths = {"ratings"})
  Page<Movie> findAll(Specification<Movie> spec, Pageable pageable);
  //  List<Movie> findByTitleContaining(String title);
  //  List<Movie> findByGenre(MovieGenre genre);
  //  List<Movie> findByGenreAndTitleContaining(MovieGenre genre, String title);
}
