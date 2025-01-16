package com.robert.projects.MovieManagement.service;

import java.util.List;

import com.robert.projects.MovieManagement.dto.request.movie.GetMoviesRequest;
import com.robert.projects.MovieManagement.dto.response.movie.GetMovie;
import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.util.MovieGenre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
  // List<GetMovie> findAll(String title, MovieGenre genre, Integer minReleaseYear, Integer maxReleaseYear);
  Page<GetMovie> findAll(GetMoviesRequest params, Pageable pageable);
  GetMovie findOneById(Long id);
  GetMovie createOne(Movie movie);
  GetMovie updateOneById(Long id, Movie movie);
  void deleteOneById(Long id);
}
