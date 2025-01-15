package com.robert.projects.MovieManagement.dto.response.error;

public class ErrorResponse<T> {
  private String message;
  private String errorMessage;
  private T errors;

  public ErrorResponse() {}

  public ErrorResponse(String message, String errorMessage, T errors) {
    this.message = message;
    this.errorMessage = errorMessage;
    this.errors = errors;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public T getErrors() {
    return errors;
  }

  public void setErrors(T errors) {
    this.errors = errors;
  }
}
