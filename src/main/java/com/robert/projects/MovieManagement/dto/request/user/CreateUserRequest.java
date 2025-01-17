package com.robert.projects.MovieManagement.dto.request.user;

import com.robert.projects.MovieManagement.persistence.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record CreateUserRequest(
        @NotBlank
        @Size(min = 3, max = 100, message = "El nombre de usuario debe tener entre 3 y 100 caracteres")
        @Pattern(regexp = "[a-zA-Z0-9-_]{3,100}")
        String username,

        @NotBlank
        @Size(min = 3, max = 32, message = "La contraseña debe ser entre 3 y 32 caraceteres")
        String password,

        @NotBlank
        @Size(min = 3, max = 32, message = "La contraseña debe ser entre 3 y 32 caraceteres")
        String repeatPassword,

        @NotBlank
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String name
) implements Serializable {
        public static User toEntity(CreateUserRequest userDto) {
                User user = new User();
                user.setUsername(userDto.username());
                user.setPassword(userDto.password());
                user.setName(userDto.name());
                user.setRepeatPassword(userDto.repeatPassword());
                return user;
        }
}
