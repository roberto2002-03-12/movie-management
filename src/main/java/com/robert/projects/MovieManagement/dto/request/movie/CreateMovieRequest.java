package com.robert.projects.MovieManagement.dto.request.movie;

import com.robert.projects.MovieManagement.persistence.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.robert.projects.MovieManagement.util.movie.MovieGenre;

import java.io.Serializable;

public record CreateMovieRequest(
        @NotBlank(message = "Título es necesario")
        @Size(min = 2, max = 100, message = "El título debe tener entre 2 y 100 caracteres")
        String title,

        @NotBlank(message = "Director es necesario")
        @Size(min = 3, max = 100, message = "El director debe tener entre 3 y 100 caracteres")
        String director,

        @NotNull(message = "Género es necesario")
        MovieGenre genre,

        @NotBlank(message = "Es necesario el año de lanzamiento")
        @Pattern(regexp = "^\\d{4}$", message = "Fecha de lanzamiento debe tener el formato YYYY")
        String realeasedYear
) implements Serializable {
  public static Movie toEntity(CreateMovieRequest dtoMovie) {
    Movie movie = new Movie();
    movie.setDirector(dtoMovie.director());
    movie.setGenre(dtoMovie.genre());
    movie.setTitle(dtoMovie.title());
    movie.setRealeasedYear(dtoMovie.realeasedYear());
    return movie;
  }
}
