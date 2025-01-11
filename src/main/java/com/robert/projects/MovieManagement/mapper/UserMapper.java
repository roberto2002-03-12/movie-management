package com.robert.projects.MovieManagement.mapper;

import java.util.List;

import com.robert.projects.MovieManagement.dto.response.user.GetUser;
import com.robert.projects.MovieManagement.dto.response.user.SaveUser;
import com.robert.projects.MovieManagement.persistence.entity.User;

public class UserMapper {

  public static GetUser toGetDto(User entity) {
    if(entity == null) return null;

    return new GetUser(
      entity.getId(),
      entity.getUsername(),
      entity.getName(),
      null
    );
  }

  public static List<GetUser> toGetDtoList(List<User> entities) {
    if(entities == null) return null;

    return entities.stream()
      .map(UserMapper::toGetDto)
      .toList();
  }

  public static User toEntity(SaveUser saveDto) {
    if(saveDto == null) return null;

    User entity = new User();
    entity.setUsername(saveDto.username());
    entity.setName(saveDto.name());
    entity.setPassword(saveDto.password());

    return entity;
  } 
}
