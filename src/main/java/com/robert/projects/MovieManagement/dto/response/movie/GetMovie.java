package com.robert.projects.MovieManagement.dto.response.movie;

import java.io.Serializable;
import java.util.List;

import com.robert.projects.MovieManagement.util.MovieGenre;

public record GetMovie(
  long id,
  String title,
  String director,
  MovieGenre genre,
  String releasedYear,
  List<GetRating> ratings
) implements Serializable {

  public static record GetRating(
    long id,
    int rating,
    String username
  ) implements Serializable {}

}
