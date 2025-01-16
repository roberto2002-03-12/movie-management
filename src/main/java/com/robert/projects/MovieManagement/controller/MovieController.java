package com.robert.projects.MovieManagement.controller;

import java.util.List;

import com.robert.projects.MovieManagement.dto.request.movie.GetMoviesRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.robert.projects.MovieManagement.dto.request.movie.CreateMovieRequest;
import com.robert.projects.MovieManagement.dto.request.movie.UpdateMovieRequest;
import com.robert.projects.MovieManagement.dto.response.movie.GetMovie;
import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {
  @Autowired
  private MovieService movieService;
  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<Page<GetMovie>> findAll(
    @Valid @ModelAttribute GetMoviesRequest params
  ) {
    return ResponseEntity.ok(movieService.findAll(params));
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetMovie> findOneById(
    @PathVariable(required = true) Long id
  ) {
    return ResponseEntity.ok(movieService.findOneById(id));
  }

  @PostMapping
  public ResponseEntity<GetMovie> createOne(
    @Valid @RequestBody CreateMovieRequest entity
  ) {
    return ResponseEntity.status(201).body(movieService.createOne(entity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<GetMovie> updateOne(
    @PathVariable String id,
    @Valid @RequestBody UpdateMovieRequest entity
  ) {
    return ResponseEntity.status(201).body(movieService.updateOneById(Long.parseLong(id), entity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteOneById(
    @PathVariable(required = true) String id
  ) {
    movieService.deleteOneById(Long.parseLong(id));
    return ResponseEntity.status(204).body("Pelicula eliminada");
  }
}
