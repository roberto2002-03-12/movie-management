package com.robert.projects.MovieManagement.service;

import java.util.List;

import com.robert.projects.MovieManagement.dto.request.user.CreateUserRequest;
import com.robert.projects.MovieManagement.dto.request.user.UpdateUserRequest;
import com.robert.projects.MovieManagement.dto.response.user.GetUser;
import com.robert.projects.MovieManagement.persistence.entity.User;

public interface UserService {
  List<GetUser> findAll(String name);
  GetUser findOneByUsername(String username);
  GetUser createOne(CreateUserRequest user);
  GetUser updateOneByUsername(String username, UpdateUserRequest user);
  void deleteOneByUsername(String username);
}
