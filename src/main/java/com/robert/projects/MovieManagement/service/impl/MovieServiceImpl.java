package com.robert.projects.MovieManagement.service.impl;

import com.robert.projects.MovieManagement.dto.request.movie.CreateMovieRequest;
import com.robert.projects.MovieManagement.dto.request.movie.GetMoviesRequest;
import com.robert.projects.MovieManagement.dto.request.movie.UpdateMovieRequest;
import com.robert.projects.MovieManagement.persistence.specification.FindAllMoviesSpecification;
import com.robert.projects.MovieManagement.util.OrderDirection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.robert.projects.MovieManagement.dto.response.movie.GetMovie;
import com.robert.projects.MovieManagement.exception.throwable.ObjectNotFoundException;
import com.robert.projects.MovieManagement.mapper.MovieMapper;
import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.persistence.repository.MovieCrudRepository;
import com.robert.projects.MovieManagement.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
  @Autowired
  private MovieCrudRepository movieCrudRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Page<GetMovie> findAll(GetMoviesRequest params) {
    String orderBy = "id";

    if(params.orderBy() != null)
      orderBy = params.orderBy().toString();

    Sort.Direction direction = params.order() == OrderDirection.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort movieSort = Sort.by(direction, orderBy);

    Pageable pageable = PageRequest.of(params.page(), params.pageSize(), movieSort);
    FindAllMoviesSpecification moviesSpecification = new FindAllMoviesSpecification(params);
    Page<Movie> entities = movieCrudRepository.findAll(moviesSpecification, pageable);
    return entities.map(MovieMapper::toGetDto);
  }

  @Override
  public GetMovie createOne(CreateMovieRequest data) {
    Movie movie = CreateMovieRequest.toEntity(data);
    return MovieMapper.toGetDto(movieCrudRepository.save(movie));
  }

  @Override
  public GetMovie findOneById(Long id) {
    return MovieMapper.toGetDto(this.findOneByIdInternal(id)); 
  }

  @Override
  public GetMovie updateOneById(Long id, UpdateMovieRequest updatedData) {
    Movie oldMovie = this.findOneByIdInternal(id);
    Movie newMovie = UpdateMovieRequest.toEntity(updatedData);
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
