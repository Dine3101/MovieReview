package org.pjadm.movie_review.controller;

import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.dto.MovieRequestDTO;
import org.pjadm.movie_review.dto.MovieResponseDTO;
import org.pjadm.movie_review.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return movieList;
    }

    @GetMapping("/{imdb-id}")
    public List<MovieResponseDTO> getMoviesByImdbId(@PathVariable("imdb-id") String imdbId){
        return movieService.getMoviesByImdbId(imdbId);
    }


    @PostMapping("")
    public List<MovieResponseDTO> saveMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        movieService.addMovie(movieRequestDTO);
        return movieService.getMoviesByImdbId(movieRequestDTO.getImdbId());
    }

    @DeleteMapping("/{imdb-id}")
    public List<MovieResponseDTO> deleteMovie(@PathVariable("imdb-id") String imdbId){
        return movieService.deleteMovie(imdbId);
    }

}
