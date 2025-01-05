package com.robert.projects.MovieManagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import com.robert.projects.MovieManagement.dto.response.ErrorResponse;

// Esto sirve para todos los controladores, aunque puedes especificar para que controladores o controlador va dirigido con
// Target all Controllers annotated with @RestController

// @ControllerAdvice(annotations = RestController.class)
// public class ExampleAdvice1 {}

// Target all Controllers within specific packages
// @ControllerAdvice("org.example.controllers")
// public class ExampleAdvice2 {}

// Target all Controllers assignable to specific classes
// @ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
// public class ExampleAdvice3 {}
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse> handleObjectNotFoundException(ObjectNotFoundException ex) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(new ErrorResponse(ex.getMessage(), null));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new ErrorResponse("Error de validación", errors));
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception ex) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(new ErrorResponse("Error interno del servidor", null));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(new ErrorResponse("Error de integridad en la base de datos", null));
  }

  @ExceptionHandler(DataAccessException.class)
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex) {
    return ResponseEntity
      .status(HttpStatus.SERVICE_UNAVAILABLE)
      .body(new ErrorResponse("Error al acceder a la base de datos", null));
  }
}
