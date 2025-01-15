package com.robert.projects.MovieManagement.mapper;

import java.util.List;

import com.robert.projects.MovieManagement.dto.response.movie.GetMovie;
import com.robert.projects.MovieManagement.dto.response.movie.SaveMovie;
import com.robert.projects.MovieManagement.persistence.entity.Movie;

public class MovieMapper {

  public static GetMovie toGetDto(Movie entity) {
    if(entity == null) return null;

    System.out.println("entity.getAverageRating(): " + entity.getAverageRating());

    return new GetMovie(
      entity.getId(),
      entity.getTitle(),
      entity.getDirector(),
      entity.getGenre(),
      entity.getRealeasedYear(),
      RatingMapper.toGetMovieRatingDtoList(entity.getRatings()) 
    );
  }

  public static List<GetMovie> toGetDtoList(List<Movie> entities) {
    if(entities == null) return null;

    return entities.stream()
      .map(MovieMapper::toGetDto)
      .toList();
  }

  public static Movie toEntity(SaveMovie saveDto) {
    if(saveDto == null) return null;

    Movie entity = new Movie();
    entity.setTitle(saveDto.title());
    entity.setDirector(saveDto.director());
    entity.setGenre(saveDto.genre());
    entity.setRealeasedYear(saveDto.releasedYear());

    return entity;
  }
}
