package org.pjadm.movie_review.service;

import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.document.Review;
import org.pjadm.movie_review.dto.MovieRequestDTO;
import org.pjadm.movie_review.dto.MovieResponseDTO;
import org.pjadm.movie_review.dto.ReviewRequestDTO;
import org.pjadm.movie_review.repository.MovieRepository;
import org.pjadm.movie_review.util.MovieOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final ReviewService reviewService;

    @Autowired
    public MovieService(MovieRepository movieRepository,ReviewService reviewService){
        this.movieRepository=movieRepository;
        this.reviewService=reviewService;
    }
    public List<Movie> getAllMovies(){
        try{
            return movieRepository.getAll();
        }catch (Exception e){
            return null;
        }
    }

    public List<MovieResponseDTO> getMoviesByImdbId(String imdbId){
        return MovieOperations.convertToReponseDTOList(movieRepository.get(imdbId));
    }

    public void addMovie(MovieRequestDTO movieRequestDTO){
        movieRepository.save(MovieOperations.convertToMovie(movieRequestDTO));
    }

    public List<MovieResponseDTO> deleteMovie(String imdbId){
        List<Movie> movieList=movieRepository.get(imdbId);
        List<MovieResponseDTO> response=MovieOperations.convertToReponseDTOList(movieList);
        reviewService.deleteReviews(movieList.get(0).getReviewIds());
        movieRepository.delete(imdbId);
        return response;
    }

    public void addReview(String imdbId, ReviewRequestDTO reviewRequestDTO){
        Review review=reviewService.addReview(reviewRequestDTO.getReviewBody());
        Movie movie=movieRepository.get(imdbId).get(0);
        movie.getReviewIds().add(review);
        movieRepository.save(movie);
    }
}
