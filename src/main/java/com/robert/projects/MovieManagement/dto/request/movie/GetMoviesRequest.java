package com.robert.projects.MovieManagement.dto.request.movie;

import com.robert.projects.MovieManagement.util.MovieGenre;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record GetMoviesRequest(
        @Size(max = 100, message = "Título debe tener máximo 200 caracteres")
        String title,
        MovieGenre genre,
        Integer minReleaseYear,
        Integer maxReleaseYear
) implements Serializable {
}
