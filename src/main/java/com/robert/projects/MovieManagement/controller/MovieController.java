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
    @Valid @ModelAttribute GetMoviesRequest params,
    @RequestParam(required = true, defaultValue = "0") Integer page,
    @RequestParam(required = true, defaultValue = "10") Integer pageSize
  ) {
    Pageable moviePageable = PageRequest.of(page, pageSize);
    return ResponseEntity.ok(movieService.findAll(params, moviePageable));
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
    Movie movie = modelMapper.map(entity, Movie.class);
    return ResponseEntity.status(201).body(movieService.createOne(movie));
  }

  @PutMapping("/{id}")
  public ResponseEntity<GetMovie> updateOne(
    @PathVariable String id,
    @Valid @RequestBody UpdateMovieRequest entity
  ) {
    Movie movie = modelMapper.map(entity, Movie.class);
    return ResponseEntity.status(201).body(movieService.updateOneById(Long.parseLong(id), movie));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteOneById(
    @PathVariable(required = true) String id
  ) {
    movieService.deleteOneById(Long.parseLong(id));
    return ResponseEntity.status(204).body("Pelicula eliminada");
  }
}
