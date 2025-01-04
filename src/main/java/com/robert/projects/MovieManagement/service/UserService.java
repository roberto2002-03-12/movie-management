package com.robert.projects.MovieManagement.service;

import java.util.List;

import com.robert.projects.MovieManagement.persistence.entity.User;

public interface UserService {
  List<User> findAll();
  List<User> findAllByName(String name);
  User findOneByUsername(String username);
  User createOne(User user);
  User updateOneByUsername(String username, User user);
  void deleteOneByUsername(String username);
}
