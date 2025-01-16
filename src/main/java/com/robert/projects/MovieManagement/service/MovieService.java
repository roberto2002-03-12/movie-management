package com.robert.projects.MovieManagement.service;

import com.robert.projects.MovieManagement.dto.request.movie.CreateMovieRequest;
import com.robert.projects.MovieManagement.dto.request.movie.GetMoviesRequest;
import com.robert.projects.MovieManagement.dto.request.movie.UpdateMovieRequest;
import com.robert.projects.MovieManagement.dto.response.movie.GetMovie;
import com.robert.projects.MovieManagement.persistence.entity.Movie;
import org.springframework.data.domain.Page;

public interface MovieService {
  Page<GetMovie> findAll(GetMoviesRequest params);
  GetMovie findOneById(Long id);
  GetMovie createOne(CreateMovieRequest movie);
  GetMovie updateOneById(Long id, UpdateMovieRequest movie);
  void deleteOneById(Long id);
}
