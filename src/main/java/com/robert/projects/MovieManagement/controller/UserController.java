package com.robert.projects.MovieManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robert.projects.MovieManagement.persistence.entity.User;
import com.robert.projects.MovieManagement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> findAll(
    @RequestParam(required = false) String name
  ) {
    return ResponseEntity.ok(userService.findAll(name));
  }

  @GetMapping("/{username}")
  public ResponseEntity<User> findOneByUsername(
    @PathVariable(required = true) String username
  ) {
    return ResponseEntity.ok(userService.findOneByUsername(username));
  }
}
