package com.robert.projects.MovieManagement.dto.request.user;

import com.robert.projects.MovieManagement.persistence.entity.User;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record UpdateUserRequest(
        @Size(min = 3, max = 32, message = "La contraseña debe ser entre 3 y 32 caraceteres")
        String password,

        @Size(min = 3, max = 32, message = "La contraseña debe ser entre 3 y 32 caraceteres")
        String repeatPassword,

        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String name
) implements Serializable {
        public static User toEntity(UpdateUserRequest userDto) {
                User user = new User();
                user.setPassword(userDto.password());
                user.setName(userDto.name());
                user.setRepeatPassword(userDto.repeatPassword());
                return user;
        }
}
