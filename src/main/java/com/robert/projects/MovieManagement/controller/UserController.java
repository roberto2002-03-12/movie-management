package com.robert.projects.MovieManagement.controller;

import java.util.List;

import com.robert.projects.MovieManagement.dto.request.user.CreateUserRequest;
import com.robert.projects.MovieManagement.dto.request.user.UpdateUserRequest;
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
  public ResponseEntity<GetUser> createOne(
          @Valid @RequestBody CreateUserRequest entity
  ) {
    User user = modelMapper.map(entity, User.class);
    return ResponseEntity.status(201).body(userService.createOne(user));
  }

  @PutMapping("/{username}")
  public ResponseEntity<GetUser> updateOne(
          @PathVariable(required = true) String username,
          @Valid @RequestBody UpdateUserRequest entity
  ) {
    System.out.println("HOLA!!!!!!!!!!");
    User user = modelMapper.map(entity, User.class);
    return ResponseEntity.status(201).body(userService.updateOneByUsername(username, user));
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<String> deleteOneById(
          @PathVariable(required = true) String username
  ) {
    userService.deleteOneByUsername(username);
    return ResponseEntity.status(204).body("Usuario eliminado");
  }
}
