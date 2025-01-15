package com.robert.projects.MovieManagement.controller;

import java.util.List;

import com.robert.projects.MovieManagement.dto.request.movie.GetMoviesRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.robert.projects.MovieManagement.dto.request.movie.CreateMovieRequest;
import com.robert.projects.MovieManagement.dto.request.movie.UpdateMovieRequest;
import com.robert.projects.MovieManagement.dto.response.movie.GetMovie;
import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.service.MovieService;
import com.robert.projects.MovieManagement.util.MovieGenre;

import jakarta.validation.Valid;

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

  @GetMapping
  public ResponseEntity<List<GetMovie>> findAll(
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
