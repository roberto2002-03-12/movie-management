package com.robert.projects.MovieManagement.persistence.entity;

import java.util.List;

// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonProperty;
import com.robert.projects.MovieManagement.util.movie.MovieGenre;

import jakarta.persistence.*;

@Entity
@NamedEntityGraph(
  name = "Movie.ratings",
  attributeNodes = @NamedAttributeNode("ratings")
)
public class Movie {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String director;

  @Enumerated(EnumType.STRING)
  private MovieGenre genre;

  @Column(name = "release_year")
  private String releaseYear;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
  private List<Rating> ratings;

  @Transient
  private Double averageRating;

  public Long getId() {
    return id;
  }

  public void setId(Long id) { this.id = id; }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public MovieGenre getGenre() {
    return genre;
  }

  public void setGenre(MovieGenre genre) {
    this.genre = genre;
  }

  public String getReleaseYear() { return releaseYear; }

  public void setReleaseYear(String releaseYear) { this.releaseYear = releaseYear; }

  public List<Rating> getRatings() {
    return ratings;
  }

  public void setRatings(List<Rating> ratings) { this.ratings = ratings; }

  public Double getAverageRating() { return averageRating; }

  public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
}
