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
  // @JsonProperty(access = JsonProperty.Access.READ_ONLY) // ya no se necesita porque estamos usando mapper
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String director;

  @Enumerated(EnumType.STRING)
  // @JsonProperty(value = "released-year")
  // con esto haces que solo sea lectura no escritura, si lo colocas en el POST lo va ignorar
  private MovieGenre genre;

  @Column(name = "realeased_year")
  // usalo para cambiar el nombre del atributo en caso de responder una respuesta api
  // @JsonProperty(value = "released-year")
  // @JsonFormat(pattern = "yyyy/MM/dd") // solo funciona para fechas
  private String realeasedYear;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
  // @JsonManagedReference // ya no se necesita porque estamos usando mapper
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

  public String getRealeasedYear() {
    return realeasedYear;
  }

  public void setRealeasedYear(String realeasedYear) {
    this.realeasedYear = realeasedYear;
  }

  public List<Rating> getRatings() {
    return ratings;
  }

  public void setRatings(List<Rating> ratings) { this.ratings = ratings; }

  public Double getAverageRating() { return averageRating; }

  public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
}
