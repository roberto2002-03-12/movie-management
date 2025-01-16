package com.robert.projects.MovieManagement.dto.request.movie;

import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.util.movie.MovieGenre;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record UpdateMovieRequest(
        @Size(min = 2, max = 100, message = "El título debe tener entre 2 y 100 caracteres")
        String title,

        @Size(min = 3, max = 100, message = "El director debe tener entre 3 y 100 caracteres")
        String director,

        MovieGenre genre,

        @Pattern(regexp = "^\\d{4}$", message = "Fecha de lanzamiento debe tener el formato YYYY")
        String realeasedYear
) implements Serializable {
  public static Movie toEntity(UpdateMovieRequest dtoMovie) {
    Movie movie = new Movie();
    movie.setDirector(dtoMovie.director());
    movie.setGenre(dtoMovie.genre());
    movie.setTitle(dtoMovie.title());
    movie.setRealeasedYear(dtoMovie.realeasedYear());
    return movie;
  }
}
