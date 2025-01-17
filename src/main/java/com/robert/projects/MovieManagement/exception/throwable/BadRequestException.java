package com.robert.projects.MovieManagement.exception.throwable;

import com.robert.projects.MovieManagement.dto.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Note: Esta clase es generica es por eso que solo tiene como parametro ErrorResponse
public class BadRequestException extends RuntimeException {
    private ErrorResponse<?> errorResponse;

    public BadRequestException(ErrorResponse<?> errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse<?> getErrorResponse() {
        return errorResponse;
    }
}
