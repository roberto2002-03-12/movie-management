package com.robert.projects.MovieManagement.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robert.projects.MovieManagement.dto.request.movie.CreateMovieRequest;
import com.robert.projects.MovieManagement.dto.request.movie.UpdateMovieRequest;
import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.service.MovieService;
import com.robert.projects.MovieManagement.util.MovieGenre;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.http.MediaType;

@RestController
@RequestMapping("/movies")
public class MovieController {
  @Autowired
  private MovieService movieService;
  @Autowired
  private ModelMapper modelMapper;

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

  @PostMapping
  public ResponseEntity<Movie> createOne(
    @Valid @RequestBody CreateMovieRequest entity
  ) {
    Movie movie = modelMapper.map(entity, Movie.class);
    return ResponseEntity.status(201).body(movieService.createOne(movie));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Movie> updateOne(
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
