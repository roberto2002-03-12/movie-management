package com.robert.projects.MovieManagement.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// utilizar record como en el DTO de response no con clases
public class CreateUserRequest {
    @NotBlank
    @Size(min = 3, max = 100, message = "El nombre de usuario debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "[a-zA-Z0-9-_]{3,100}")
    private String username;

    @NotBlank
    @Size(min = 3, max = 32, message = "La contraseña debe ser entre 3 y 32 caraceteres")
    private String password;

    @NotBlank
    @Size(min = 3, max = 32, message = "La contraseña debe ser entre 3 y 32 caraceteres")
    private String repeatPassword;

    @NotBlank
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
