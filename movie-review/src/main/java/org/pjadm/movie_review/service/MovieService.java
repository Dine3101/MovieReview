package org.pjadm.movie_review.service;

import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }
    public List<Movie> getAllMovies(){
        try{
            return movieRepository.getAll();
        }catch (Exception e){
            System.out.println("Error while fetching all movies.");
            return null;
        }
    }
}
