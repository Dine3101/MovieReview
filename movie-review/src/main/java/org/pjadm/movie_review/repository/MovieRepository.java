package org.pjadm.movie_review.repository;

import org.pjadm.movie_review.document.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository implements RepositoryTemplate<Movie>{

    private MongoTemplate mongoTemplate;

    @Autowired
    public MovieRepository(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }
    @Override
    public void save(Movie document) {

    }

    @Override
    public Movie get(String id) {
        return null;
    }

    @Override
    public List<Movie> getAll() {
        return mongoTemplate.findAll(Movie.class);
    }
}
