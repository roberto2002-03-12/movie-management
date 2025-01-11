package com.robert.projects.MovieManagement.dto.response.movie;

import java.io.Serializable;

import com.robert.projects.MovieManagement.util.MovieGenre;

public record SaveMovie(
  String title,
  String director,
  MovieGenre genre,
  int releasedYear
) implements Serializable { }
