package org.pjadm.movie_review.controller;

import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.dto.MovieRequestDTO;
import org.pjadm.movie_review.dto.MovieResponseDTO;
import org.pjadm.movie_review.dto.ReviewRequestDTO;
import org.pjadm.movie_review.service.MovieService;
import org.pjadm.movie_review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private MovieService movieService;

    private ReviewService reviewService;

    @Autowired
    public MovieController(MovieService movieService,ReviewService reviewService){

        this.movieService=movieService;
        this.reviewService=reviewService;
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

    @PostMapping("/{imdb-id}")
    public List<MovieResponseDTO> addMovieReview(@PathVariable("imdb-id") String imdbId,@RequestBody ReviewRequestDTO reviewRequestDTO){
        movieService.addReview(imdbId,reviewRequestDTO);
        return movieService.getMoviesByImdbId(imdbId);
    }

    @DeleteMapping("/{imdb-id}")
    public List<MovieResponseDTO> deleteMovie(@PathVariable("imdb-id") String imdbId){
        return movieService.deleteMovie(imdbId);
    }

    @DeleteMapping("/{imdb-id}/{review-id}")
    public List<MovieResponseDTO> deleteMovieReview(@PathVariable("imdb-id") String imdbId,@PathVariable("review-id") String reviewId){
        movieService.deleteMovieReview(imdbId,reviewId);
        reviewService.deleteReview(reviewId);
        return movieService.getMoviesByImdbId(imdbId);
    }

}
