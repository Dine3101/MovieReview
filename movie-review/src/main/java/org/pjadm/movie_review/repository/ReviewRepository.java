package org.pjadm.movie_review.repository;

import org.bson.types.ObjectId;
import org.pjadm.movie_review.document.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository implements RepositoryTemplate<Review> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ReviewRepository (MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }
    @Override
    public Review save(Review review) {
        review=mongoTemplate.save(review);
        return review;
    }

    @Override
    public List<Review> getAll() {
        return mongoTemplate.findAll(Review.class,"reviews");
    }

    @Override
    public void delete(String id) {
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Review.class);
    }

    @Override
    public List<Review> get(String id){
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.find(query,Review.class);
    }
}

