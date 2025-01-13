package com.robert.projects.MovieManagement.dto.request.user;

import jakarta.validation.constraints.Size;

public class UpdateUserRequest {
    @Size(min = 3, max = 32, message = "La contraseña debe ser entre 3 y 32 caraceteres")
    private String password;

    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

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
}
