package com.robert.projects.MovieManagement.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.robert.projects.MovieManagement.persistence.entity.Rating;

public interface RatingCrudRepository extends JpaRepository<Rating, Long> {
  List<Rating> findByMovieId(Long movieId);
  List<Rating> findByUserName(String name);

  // @Query("SELECT r FROM Rating r JOIN r.user u WHERE u.name = ?1")
  // List<Rating> findByUsername(String name);
}
