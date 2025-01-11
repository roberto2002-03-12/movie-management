package com.robert.projects.MovieManagement.dto.response.user;

import java.io.Serializable;
import java.util.List;

public record GetUser(
  long id,
  String username,
  String name,
  List<GetRating> ratings
) implements Serializable {
  public static record GetRating(
    long id,
    String movieTitle,
    long movieId,
    int rating
  ) implements Serializable {}
}
