package com.robert.projects.MovieManagement.service;

import java.util.List;

import com.robert.projects.MovieManagement.persistence.entity.Rating;

public interface RatingService {
  List<Rating> findAll();
  List<Rating> findAllByMovieId(Long movieId);
  List<Rating> findAllByUserUsername(String username);
  
  Rating findOneById(Long id);
  Rating createOne(Rating rating);
  Rating updateOneById(Long id, Rating rating);

  void deleteOneById(Long id);
}
