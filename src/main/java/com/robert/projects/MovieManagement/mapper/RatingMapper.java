package com.robert.projects.MovieManagement.mapper;

import java.util.List;

import com.robert.projects.MovieManagement.dto.response.movie.GetMovie;
import com.robert.projects.MovieManagement.dto.response.user.GetUser;
import com.robert.projects.MovieManagement.persistence.entity.Rating;

public class RatingMapper {
  public static List<GetMovie.GetRating> toGetMovieRatingDtoList(List<Rating> entity) {
    if(entity == null) return null;

    return entity.stream()
      .map(RatingMapper::toGetMovieRatingDto)
      .toList();
  }

  public static GetMovie.GetRating toGetMovieRatingDto(Rating entity) {
    if(entity == null) return null;

    String username = entity.getUser() != null
      ? entity.getUser().getUsername()
      : null;

    return new GetMovie.GetRating(
      entity.getId(),
      entity.getRating(),
      username
    );
  }

  public static List<GetUser.GetRating> toGetUserRatingDtoList(List<Rating> entity) {
    if(entity == null) return null;

    return entity.stream()
      .map(RatingMapper::toGetUserRatingDto)
      .toList();
  }

  public static GetUser.GetRating toGetUserRatingDto(Rating entity) {
    if(entity == null) return null;

    String movieTitle = entity.getMovie() != null
      ? entity.getMovie().getTitle()
      : null;

    return new GetUser.GetRating(
      entity.getId(),
      movieTitle,
      entity.getMovieId(),
      entity.getRating()
    );
  }
}
