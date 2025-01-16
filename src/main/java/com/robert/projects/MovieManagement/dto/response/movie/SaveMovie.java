package com.robert.projects.MovieManagement.dto.response.movie;

import java.io.Serializable;

import com.robert.projects.MovieManagement.util.movie.MovieGenre;

public record SaveMovie(
  String title,
  String director,
  MovieGenre genre,
  String releasedYear
) implements Serializable { }
