package com.robert.projects.MovieManagement.service;

import java.util.List;

import com.robert.projects.MovieManagement.dto.response.user.GetUser;
import com.robert.projects.MovieManagement.persistence.entity.User;

public interface UserService {
  List<GetUser> findAll(String name);
  GetUser findOneByUsername(String username);
  GetUser createOne(User user);
  GetUser updateOneByUsername(String username, User user);
  void deleteOneByUsername(String username);
}
