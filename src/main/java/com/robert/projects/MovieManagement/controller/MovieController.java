package com.robert.projects.MovieManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robert.projects.MovieManagement.persistence.entity.Movie;
import com.robert.projects.MovieManagement.service.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {
  @Autowired
  private MovieService movieService;

  @RequestMapping()
  @ResponseBody()
  public List<Movie> findAll() {
    return movieService.findAll();
  }

}
