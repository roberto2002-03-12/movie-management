package com.robert.projects.MovieManagement.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.util.MovieGenre;

public interface MovieCrudRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
  // Containing es como el like de SQL
  @EntityGraph(value = "Movie.ratings")
  List<Movie> findAll();
  List<Movie> findByTitleContaining(String title);
  List<Movie> findByGenre(MovieGenre genre);
  List<Movie> findByGenreAndTitleContaining(MovieGenre genre, String title);
}
