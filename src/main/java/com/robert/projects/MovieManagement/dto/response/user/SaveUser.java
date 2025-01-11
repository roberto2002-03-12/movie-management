package com.robert.projects.MovieManagement.dto.response.user;

import java.io.Serializable;

public record SaveUser(
  String username,
  String name,
  String password,
  String passwordRepeated
) implements Serializable { }
