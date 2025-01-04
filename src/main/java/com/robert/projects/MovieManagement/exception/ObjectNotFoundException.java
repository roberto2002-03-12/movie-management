package com.robert.projects.MovieManagement.exception;

public class ObjectNotFoundException extends RuntimeException {

  private final String objectNotFoundName;
  @SuppressWarnings("unused")
  private final Throwable cause;

  public ObjectNotFoundException(String message) {
    this.objectNotFoundName = message;
    this.cause = null;
  }

  public ObjectNotFoundException(String message, Throwable cause) {
    this.objectNotFoundName = message;
    this.cause = cause;
  }

  @Override
  public String getMessage() {
    return super.getMessage()
      .concat("(object not found: ")
      .concat(this.objectNotFoundName)
      .concat(")");
  }

  public String getObjectNotFoundName() {
    return this.objectNotFoundName;
  }

}
