package com.robert.projects.MovieManagement.dto.request.movie;

import com.robert.projects.MovieManagement.util.OrderDirection;
import com.robert.projects.MovieManagement.util.movie.MovieGenre;
import com.robert.projects.MovieManagement.util.movie.MovieOrderBy;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
        Double maxAverageRating,

        @NotNull(message = "La página es requerida")
        Integer page,

        @NotNull(message = "El tamaño de la página es requerido")
        @Min(value = 10, message = "El limite minimo es 10")
        @Max(value = 50, message = "El limite maximo es 50")
        Integer pageSize,

        MovieOrderBy orderBy,

        OrderDirection order

        /*
        * Podrías declarar simplemente el objeto Pageable pageable y obtendría los datos de manera
        * automatica siempre y cuando los queries sean size, page y sort, sin embargo, esto quitaría
        * la personalización asi que por ese motivo no lo hago */
) implements Serializable { }
