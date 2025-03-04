package org.pjadm.movie_review.controller;

import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    @GetMapping("/all")
    public List<Movie> getAllMovies(){
        List<Movie> movieList=movieService.getAllMovies();
        System.out.println(movieList);
        return movieList;
    }

}
