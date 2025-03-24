package org.pjadm.movie_review.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.dto.MovieRequestDTO;
import org.pjadm.movie_review.dto.MovieResponseDTO;
import org.pjadm.movie_review.dto.ReviewRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieOperations {

    private static final Movie movie;
    private static final MovieRequestDTO movieRequestDTO;
    static {
        movie=new Movie();
        movieRequestDTO=new MovieRequestDTO();
    }
    public static Movie convertToMovie(MovieRequestDTO dto){
        movie.setImdbId(dto.getImdbId());
        movie.setTitle(dto.getTitle());
        movie.setTrailerLink(dto.getTrailerLink());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setGenres(dto.getGenres());
        movie.setPoster(dto.getPoster());
        movie.setBackdrops(dto.getBackdrops());
        return movie;
    }

    public static MovieResponseDTO convertToResponseDTO(Movie movie){
        MovieResponseDTO dto=new MovieResponseDTO();
        dto.setImdbId(movie.getImdbId());
        dto.setTitle(movie.getTitle());
        dto.setBackdrops(movie.getBackdrops());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setPoster(movie.getPoster());
        dto.setGenres(movie.getGenres());
        dto.setTrailerLink(movie.getTrailerLink());
        dto.setReviewIds(movie.getReviewIds());
        return dto;
    }

    public static MovieRequestDTO convertToRequestDTO(Movie movie){
        MovieRequestDTO dto=movieRequestDTO;
        dto.setImdbId(movie.getImdbId());
        dto.setTitle(movie.getTitle());
        dto.setBackdrops(movie.getBackdrops());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setPoster(movie.getPoster());
        dto.setGenres(movie.getGenres());
        dto.setTrailerLink(movie.getTrailerLink());
        return dto;
    }

    public static List<MovieResponseDTO> convertToReponseDTOList(List<Movie> movieList){
        List<MovieResponseDTO> dtoList=new ArrayList<>();
        for(Movie movie:movieList){
            dtoList.add(convertToResponseDTO(movie));
        }
        return dtoList;
    }

    public static Movie getSampleMovie(){
        movie.setImdbId("tt3935174");
        movie.setTitle("RRR - Tamil Dubbed");
        movie.setTrailerLink("");
        movie.setReleaseDate("");
        movie.setGenres(new ArrayList<>());
        movie.setPoster("");
        movie.setBackdrops(new ArrayList<>());
        movie.setReviewIds(new ArrayList<>());
        return movie;
    }

    public static MovieRequestDTO getSampleMovieRequestDTO(){
        return convertToRequestDTO(getSampleMovie());
    }

    public static String getSampleMovieRequestDTOJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(getSampleMovieRequestDTO());
    }

    public static ReviewRequestDTO getSampleReviewRequestDTO(){
        String reviewBody="Sample Review for Troll Movie";
        return new ReviewRequestDTO(reviewBody);
    }

    public static String getSampleReviewRequestDTOJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(getSampleReviewRequestDTO());
    }
}
