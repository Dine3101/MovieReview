package org.pjadm.movie_review.repository;

import org.pjadm.movie_review.document.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository implements RepositoryTemplate<Movie>{

    private final MongoTemplate mongoTemplate;


    @Autowired
    public MovieRepository(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }
    @Override
    public void save(Movie movie) {
        mongoTemplate.save(movie);
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
}
