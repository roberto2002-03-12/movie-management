package com.robert.projects.MovieManagement.service.impl;

import java.util.List;

import com.robert.projects.MovieManagement.dto.request.user.CreateUserRequest;
import com.robert.projects.MovieManagement.dto.request.user.UpdateUserRequest;
import com.robert.projects.MovieManagement.service.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.projects.MovieManagement.dto.response.user.GetUser;
import com.robert.projects.MovieManagement.exception.throwable.ObjectNotFoundException;
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
  public GetUser createOne(CreateUserRequest userDto) {
    User user = CreateUserRequest.toEntity(userDto);
    PasswordValidator.validatePassword(user.getPassword(), user.getRepeatPassword());
    return UserMapper.toGetDto(userCrudRepository.save(user));
  }

  @Override
  public GetUser updateOneByUsername(String username, UpdateUserRequest userDto) {
    User oldUser = this.findOneByUsernameInternal(username);
    User user = UpdateUserRequest.toEntity(userDto);

    if(user.getName() != null && !user.getName().isEmpty())
      oldUser.setName(user.getName());

    if(user.getPassword() != null && !user.getPassword().isEmpty()) {
      PasswordValidator.validatePassword(user.getPassword(), user.getRepeatPassword());
      oldUser.setPassword(user.getPassword());
    }

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
