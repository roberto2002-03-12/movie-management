package com.robert.projects.MovieManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.projects.MovieManagement.exception.throwable.ObjectNotFoundException;
import com.robert.projects.MovieManagement.persistence.entity.Rating;
import com.robert.projects.MovieManagement.persistence.repository.RatingCrudRepository;
import com.robert.projects.MovieManagement.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
  @Autowired
  private RatingCrudRepository ratingCrudRepository;

  @Override
  public List<Rating> findAll() {
    return ratingCrudRepository.findAll();
  }

  @Override
  public List<Rating> findAllByMovieId(Long movieId) {
    return ratingCrudRepository.findByMovieId(movieId);
  }

  @Override
  public List<Rating> findAllByUserUsername(String username) {
    return ratingCrudRepository.findByUserUsername(username);
  }

  @Override
  public Rating findOneById(Long id) {
    return ratingCrudRepository.findById(id)
      .orElseThrow(() -> new ObjectNotFoundException("[rating: " + Long.toString(id) + " ]"));
  }

  @Override
  public Rating createOne(Rating rating) {
    return ratingCrudRepository.save(rating);
  }

  @Override
  public Rating updateOneById(Long id, Rating rating) {
    Rating oldRating = this.findOneById(id);
    oldRating.setRating(rating.getRating());
    return ratingCrudRepository.save(oldRating);
  }

  @Override
  public void deleteOneById(Long id) {
    Rating rating = this.findOneById(id);

    if(ratingCrudRepository.existsById(id)) {
      ratingCrudRepository.delete(rating);
      return;
    }

    throw new ObjectNotFoundException("[rating: " + Long.toString(id) + " ]");
  }
  
}
