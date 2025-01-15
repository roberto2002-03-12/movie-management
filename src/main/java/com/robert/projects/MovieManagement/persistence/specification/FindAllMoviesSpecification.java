package com.robert.projects.MovieManagement.persistence.specification;

import com.robert.projects.MovieManagement.dto.request.movie.GetMoviesRequest;
import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.persistence.entity.Rating;
import com.robert.projects.MovieManagement.util.MovieGenre;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FindAllMoviesSpecification implements Specification<Movie> {
    private String title;
    private MovieGenre genre;
    private Integer minReleaseYear;
    private Integer maxReleaseYear;
    private Double minAverageRating;
    private Double maxAverageRating;

    public FindAllMoviesSpecification(GetMoviesRequest params) {
        this.title = params.title();
        this.genre = params.genre();
        this.minReleaseYear = params.minReleaseYear();
        this.maxReleaseYear = params.maxReleaseYear();
        this.maxAverageRating = params.maxAverageRating();
        this.minAverageRating = params.minAverageRating();
    }

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // root: from movie
        // query: select donde se coloca los criterios de consulta
        // criteriaBuilder: permite construir predicados y expresiones SQL

        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.hasText(title))
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + title.toLowerCase() + "%"));

        if(genre != null)
            predicates.add(criteriaBuilder.equal(root.get("genre"), genre));

        if(minReleaseYear != null && minReleaseYear.intValue() > 0)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("realeasedYear"), minReleaseYear.toString()));

        if(maxReleaseYear != null && maxReleaseYear.intValue() > 0) {
            if(minReleaseYear != null && maxReleaseYear.intValue() > minReleaseYear.intValue())
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("realeasedYear"), maxReleaseYear.toString()));
            else if(minReleaseYear == null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("realeasedYear"), maxReleaseYear.toString()));
        }

        if(this.minAverageRating != null && this.minAverageRating >= 1 && this.minAverageRating <= 4) {
            Subquery<Double> averageRatingSubquery = query.subquery(Double.class); // creación de subquery definiendo que el valor de retorno va ser un double
            Root<Rating> ratingRoot = averageRatingSubquery.from(Rating.class); // creación de root rating, es decir from rating

            averageRatingSubquery.select(criteriaBuilder.avg(ratingRoot.get("rating"))); // select avg(rating)

            Predicate movieIdEqual = criteriaBuilder.equal(root.get("id"), ratingRoot.get("movieId")); // where movie.id = rating.movieId
            averageRatingSubquery.where(movieIdEqual); // agregar filtro al subquery

            // una vez declarado los filtros del select interno, se agrega el resultado del filtro que son números a la condicional greater than equal
            Predicate averageRatingGreaterThanEqual = criteriaBuilder.greaterThanOrEqualTo(averageRatingSubquery, this.minAverageRating);
            predicates.add(averageRatingGreaterThanEqual); // se agrega a las demás queries
        }

        if(this.maxAverageRating != null && this.maxAverageRating <= 5 && this.maxAverageRating >= 1) {
            Subquery<Double> averageRatingSubquery = query.subquery(Double.class);
            Root<Rating> ratingRoot = averageRatingSubquery.from(Rating.class);

            averageRatingSubquery.select(criteriaBuilder.avg(ratingRoot.get("rating")));

            Predicate movieIdEqual = criteriaBuilder.equal(root.get("id"), ratingRoot.get("movieId"));
            averageRatingSubquery.where(movieIdEqual);

            Predicate averageRatingLessThanEqual = criteriaBuilder.lessThanOrEqualTo(averageRatingSubquery, this.maxAverageRating);
            predicates.add(averageRatingLessThanEqual);
        }

        query.distinct(true);

        // con esto puedes indicar si le dejas a Java que defina el tamaño de un array "new Predicate[0]"
        // es bueno porque puede suceder que no te pasen ningún parametro
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
