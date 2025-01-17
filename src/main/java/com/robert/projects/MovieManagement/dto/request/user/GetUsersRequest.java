package com.robert.projects.MovieManagement.dto.request.user;

import com.robert.projects.MovieManagement.util.OrderDirection;
import com.robert.projects.MovieManagement.util.user.UserOrderBy;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record GetUsersRequest(
        @Size(max = 100, message = "Nombre debe tener máximo 100 caracteres")
        String name,

        @Size(max = 100, message = "Nombre de usuario debe tener máximo 100 caracteres")
        String username,

        @NotNull(message = "La página es requerida")
        Integer page,

        @NotNull(message = "El tamaño de la página es requerido")
        @Min(value = 10, message = "El limite minimo es 10")
        @Max(value = 50, message = "El limite maximo es 50")
        Integer pageSize,

        UserOrderBy orderBy,

        OrderDirection order
) implements Serializable { }
