package com.robert.projects.MovieManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.service.MovieService;
import com.robert.projects.MovieManagement.util.MovieGenre;

@RestController
@RequestMapping("/movies")
public class MovieController {
  @Autowired
  private MovieService movieService;

  // Note: por cada petición se esta haciendo un select one, haciendo que el tiempo de respuesta sea mayor
  // ToDo: ver una manera de evitar esto
  @GetMapping
  public ResponseEntity<List<Movie>> findAll(
    @RequestParam(required = false) String title,
    @RequestParam(required = false) MovieGenre genre
  ) {
    return ResponseEntity.ok(movieService.findAll(title, genre));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> findOneById(
    @PathVariable(required = true) Long id
  ) {
    return ResponseEntity.ok(movieService.findOneById(id));
  }
}
