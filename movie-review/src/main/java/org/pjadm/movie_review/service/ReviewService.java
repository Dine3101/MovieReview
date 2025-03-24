package org.pjadm.movie_review.service;

import org.pjadm.movie_review.document.Review;
import org.pjadm.movie_review.dto.ReviewRequestDTO;
import org.pjadm.movie_review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService (ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }

    public Review addReview(String reviewBody){
        LocalDateTime currentTime=LocalDateTime.now();
        Review review=reviewRepository.save(new Review(reviewBody,currentTime,currentTime));
        return review;
    }

    public void deleteReviews(List<Review> reviewList){
        for(Review review:reviewList){
            reviewRepository.delete(review.getId().toString());
        }
    }
    public void deleteReview(String reviewId){
        reviewRepository.delete(reviewId);
    }


}
