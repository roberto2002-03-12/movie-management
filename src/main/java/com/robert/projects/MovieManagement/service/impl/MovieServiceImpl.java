package com.robert.projects.MovieManagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.projects.MovieManagement.dto.response.movie.GetMovie;
import com.robert.projects.MovieManagement.exception.ObjectNotFoundException;
import com.robert.projects.MovieManagement.mapper.MovieMapper;
import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.persistence.repository.MovieCrudRepository;
import com.robert.projects.MovieManagement.service.MovieService;
import com.robert.projects.MovieManagement.util.MovieGenre;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

@Service
public class MovieServiceImpl implements MovieService {
  @Autowired
  private MovieCrudRepository movieCrudRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<GetMovie> findAll(String title, MovieGenre genre) {
    // return movieCrudRepository.findAll();
    return MovieMapper.toGetDtoList(movieCrudRepository.findAll((root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      // Esto evita el N+1 sin embargo, no es la solución final
      root.fetch("ratings", JoinType.LEFT);
      
      if (title != null && !title.isEmpty())
        predicates.add(cb.like(root.get("title"), "%" + title + "%"));
      
      if (genre != null)
        predicates.add(cb.equal(root.get("genre"), genre));

      query.distinct(true);

      return cb.and(predicates.toArray(new Predicate[0]));
    }));
  }

  @Override
  public GetMovie createOne(Movie movie) {
    return MovieMapper.toGetDto(movieCrudRepository.save(movie));
  }

  @Override
  public GetMovie findOneById(Long id) {
    return MovieMapper.toGetDto(this.findOneByIdInternal(id)); 
  }

  @Override
  public GetMovie updateOneById(Long id, Movie newMovie) {
    Movie oldMovie = this.findOneByIdInternal(id);

    // oldMovie.setGenre(newMovie.getGenre());
    // oldMovie.setTitle(newMovie.getTitle());
    // oldMovie.setRealeasedYear(newMovie.getRealeasedYear());
    // oldMovie.setDirector(newMovie.getDirector());
    modelMapper.map(newMovie, oldMovie);

    return MovieMapper.toGetDto(movieCrudRepository.save(oldMovie));
  }

  private Movie findOneByIdInternal(Long id) {
    return movieCrudRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ObjectNotFoundException("[movie: " + Long.toString(id) + " ]")
      );
  }

  @Override
  public void deleteOneById(Long id) {
    Movie movie = this.findOneByIdInternal(id);
    movieCrudRepository.delete(movie);
  }

}
