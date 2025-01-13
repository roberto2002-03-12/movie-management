package com.robert.projects.MovieManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.projects.MovieManagement.dto.response.user.GetUser;
import com.robert.projects.MovieManagement.exception.ObjectNotFoundException;
import com.robert.projects.MovieManagement.mapper.UserMapper;
import com.robert.projects.MovieManagement.persistence.entity.User;
import com.robert.projects.MovieManagement.persistence.repository.UserCrudRepository;
import com.robert.projects.MovieManagement.service.UserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserCrudRepository userCrudRepository;

  @Override
  public List<GetUser> findAll(String name) {
    if(name == null || name.isEmpty())
      return UserMapper.toGetDtoList(userCrudRepository.findAll());

    return UserMapper.toGetDtoList(userCrudRepository.findByNameContaining(name));
  }

  // @Override
  // public List<User> findAllByName(String name) {
  //   return userCrudRepository.findByNameContaining(name);
  // }

  @Override
  public GetUser findOneByUsername(String username) {
    return UserMapper.toGetDto(this.findOneByUsernameInternal(username));
  }

  @Override
  public GetUser createOne(User user) {
    return UserMapper.toGetDto(userCrudRepository.save(user));
  }

  @Override
  public GetUser updateOneByUsername(String username, User user) {
    User oldUser = this.findOneByUsernameInternal(username);

    if(user.getName() != null && !user.getName().isEmpty())
      oldUser.setName(user.getName());

    if(user.getPassword() != null && !user.getPassword().isEmpty())
      oldUser.setPassword(user.getPassword());

    // ToDo: valdiate password

    return UserMapper.toGetDto(userCrudRepository.save(oldUser));
  }

  @Override
  @Transactional
  public void deleteOneByUsername(String username) {
    int isDeleted = userCrudRepository.deleteOneByUsername(username);

    if(isDeleted != 1)
      throw new ObjectNotFoundException("[user: " + username + " ]");
  }

  private User findOneByUsernameInternal(String username) {
    return userCrudRepository.findByUsername(username)
      .orElseThrow(() ->
        new ObjectNotFoundException("[user: " + username + " ]")
      );
  }

}
