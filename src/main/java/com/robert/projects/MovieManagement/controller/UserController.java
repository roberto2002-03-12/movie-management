package com.robert.projects.MovieManagement.controller;

import java.util.List;

import com.robert.projects.MovieManagement.dto.request.user.CreateUserRequest;
import com.robert.projects.MovieManagement.dto.request.user.UpdateUserRequest;
import com.robert.projects.MovieManagement.dto.response.ErrorResponse;
import com.robert.projects.MovieManagement.exception.InvalidPasswordException;
import com.robert.projects.MovieManagement.persistence.entity.User;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.robert.projects.MovieManagement.dto.response.user.GetUser;
import com.robert.projects.MovieManagement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<List<GetUser>> findAll(
    @RequestParam(required = false) String name
  ) {
    return ResponseEntity.ok(userService.findAll(name));
  }

  @GetMapping("/{username}")
  public ResponseEntity<GetUser> findOneByUsername(
    @PathVariable(required = true) String username
  ) {
    return ResponseEntity.ok(userService.findOneByUsername(username));
  }

  @PostMapping
  public ResponseEntity<Object> createOne(
          @Valid @RequestBody CreateUserRequest entity
  ) {
    try {
      User user = modelMapper.map(entity, User.class);
      return ResponseEntity.status(201).body(userService.createOne(user));
    } catch (Exception e) {
      if(e.getClass() == InvalidPasswordException.class)
        return ResponseEntity.status(400).body(
                new ErrorResponse(e.getMessage(), null)
        );
      throw e;
    }
  }

  @PutMapping("/{username}")
  public ResponseEntity<Object> updateOne(
          @PathVariable(required = true) String username,
          @Valid @RequestBody UpdateUserRequest entity
  ) {
    try {
      User user = modelMapper.map(entity, User.class);
      return ResponseEntity.status(201).body(userService.updateOneByUsername(username, user));
    } catch (Exception e) {
      if(e.getClass() == InvalidPasswordException.class)
        return ResponseEntity.status(400).body(
                new ErrorResponse(e.getMessage(), null)
        );
      throw e;
    }
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<String> deleteOneById(
          @PathVariable(required = true) String username
  ) {
    userService.deleteOneByUsername(username);
    return ResponseEntity.status(204).body("Usuario eliminado");
  }
}
