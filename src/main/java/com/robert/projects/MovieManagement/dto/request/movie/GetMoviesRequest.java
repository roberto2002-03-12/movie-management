package com.robert.projects.MovieManagement.dto.request.movie;

import com.robert.projects.MovieManagement.util.MovieGenre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record GetMoviesRequest(
        @Size(max = 100, message = "Título debe tener máximo 200 caracteres")
        String title,
        MovieGenre[] genres,
        Integer minReleaseYear,
        Integer maxReleaseYear,

        @Max(value = 4, message = "Rating máximo es 4")
        @Min(value = 1, message = "Rating mínimo es 1")
        Double minAverageRating,

        @Max(value = 5, message = "Máximo 5 de rating")
        @Min(value = 1, message = "Mínimo 1 de rating")
        Double maxAverageRating
) implements Serializable { }
