package com.robert.projects.MovieManagement.persistence.specification;

import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.util.MovieGenre;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FindAllMoviesSpecification implements Specification<Movie> {
    private String title;
    private MovieGenre genre;
    private Integer minReleaseYear;
    private Integer maxReleaseYear;

    public FindAllMoviesSpecification(String title, MovieGenre genre, Integer minReleaseYear, Integer maxReleaseYear) {
        this.title = title;
        this.genre = genre;
        this.minReleaseYear = minReleaseYear;
        this.maxReleaseYear = maxReleaseYear;
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

        query.distinct(true);

        // con esto puedes indicar si le dejas a Java que defina el tamaño de un array "new Predicate[0]"
        // es bueno porque puede suceder que no te pasen ningún parametro
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
