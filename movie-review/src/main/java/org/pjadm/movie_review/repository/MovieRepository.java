package org.pjadm.movie_review.repository;

import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.document.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository implements RepositoryTemplate<Movie>{

    private final MongoTemplate mongoTemplate;

    private final ReviewRepository reviewRepository;

    @Autowired
    public MovieRepository(MongoTemplate mongoTemplate,ReviewRepository reviewRepository){
        this.mongoTemplate=mongoTemplate;
        this.reviewRepository=reviewRepository;
    }
    @Override
    public Movie save(Movie movie) {

        movie=mongoTemplate.save(movie);
        return movie;
    }

    @Override
    public List<Movie> get(String imdbId) {
        Query query=new Query();
        query.addCriteria(Criteria.where("imdbId").is(imdbId));
        return mongoTemplate.find(query,Movie.class);
    }

    @Override
    public void delete(String imdbId){
        Query query=new Query();
        query.addCriteria(Criteria.where("imdbId").is(imdbId));
        mongoTemplate.remove(query,Movie.class);
    }
    @Override
    public List<Movie> getAll() {
        return mongoTemplate.findAll(Movie.class,"movies");
    }

    public void addReview(String imdbId, Review review){
        reviewRepository.save(review);
        List<Movie> movieList=get(imdbId);
        Movie movie=movieList.get(0);
        movie.getReviewIds().add(review);
        save(movie);
    }



}
