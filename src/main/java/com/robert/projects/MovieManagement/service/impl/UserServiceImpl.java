package com.robert.projects.MovieManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.projects.MovieManagement.exception.ObjectNotFoundException;
import com.robert.projects.MovieManagement.persistence.entity.User;
import com.robert.projects.MovieManagement.persistence.repository.UserCrudRepository;
import com.robert.projects.MovieManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserCrudRepository userCrudRepository;

  @Override
  public List<User> findAll(String name) {
    if(name == null || name.isEmpty())
      return userCrudRepository.findAll();

    return userCrudRepository.findByNameContaining(name);
  }

  // @Override
  // public List<User> findAllByName(String name) {
  //   return userCrudRepository.findByNameContaining(name);
  // }

  @Override
  public User findOneByUsername(String username) {
    return userCrudRepository.findByUsername(username)
      .orElseThrow(() ->
        new ObjectNotFoundException("[user: " + username + " ]")
      );
  }

  @Override
  public User createOne(User user) {
    return userCrudRepository.save(user);
  }

  @Override
  public User updateOneByUsername(String username, User user) {
    User oldUser = this.findOneByUsername(username);

    oldUser.setName(user.getName());
    oldUser.setPassword(user.getPassword());

    // ToDo: valdiate password

    return userCrudRepository.save(oldUser);
  }

  @Override
  public void deleteOneByUsername(String username) {
    int isDeleted = userCrudRepository.deleteByUsername(username);

    if(isDeleted != 1)
      throw new ObjectNotFoundException("[user: " + username + " ]");
  }

}
