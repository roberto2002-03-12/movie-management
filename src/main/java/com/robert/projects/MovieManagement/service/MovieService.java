package com.robert.projects.MovieManagement.service;

import java.util.List;

import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.util.MovieGenre;

public interface MovieService {
  List<Movie> findAll(String title, MovieGenre genre);
  Movie findOneById(Long id);
  Movie createOne(Movie movie);
  Movie updateOneById(Long id, Movie movie);
  void deleteOneById(Long id);
}
