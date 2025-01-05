package com.robert.projects.MovieManagement.dto.request.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.robert.projects.MovieManagement.util.MovieGenre;

public class CreateMovieRequest {
  @NotBlank(message = "Título es necesario")
  @Size(min = 2, max = 100, message = "El título debe tener entre 2 y 100 caracteres")
  private String title;

  @NotBlank(message = "Director es necesario")
  @Size(min = 3, max = 100, message = "El director debe tener entre 3 y 100 caracteres")
  private String director;

  @NotNull(message = "Género es necesario")
  private MovieGenre genre;

  @NotBlank(message = "Es necesario el año de lanzamiento")
  @Pattern(regexp = "^\\d{4}$", message = "Fecha de lanzamiento debe tener el formato YYYY")
  private String realeasedYear;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public MovieGenre getGenre() {
    return genre;
  }

  public void setGenre(MovieGenre genre) {
    this.genre = genre;
  }

  public String getRealeasedYear() {
    return realeasedYear;
  }

  public void setRealeasedYear(String realeasedYear) {
    this.realeasedYear = realeasedYear;
  }
}
