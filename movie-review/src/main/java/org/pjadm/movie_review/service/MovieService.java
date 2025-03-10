package org.pjadm.movie_review.service;

import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.dto.MovieRequestDTO;
import org.pjadm.movie_review.dto.MovieResponseDTO;
import org.pjadm.movie_review.repository.MovieRepository;
import org.pjadm.movie_review.util.MovieOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private final MovieRepository movieRepository;


    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
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
        List<MovieResponseDTO> response=MovieOperations.convertToReponseDTOList(movieRepository.get(imdbId));
        movieRepository.delete(imdbId);
        return response;
    }
}
