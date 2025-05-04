package org.pjadm.movie_review;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pjadm.movie_review.document.Review;
import org.pjadm.movie_review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Disabled
public class ReviewAPITest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("Review - Repository : GetAll method test")
    public void reviewRepositoryFindAllMethodTest(){
        List<Review> reviewList=reviewRepository.getAll();
        assertEquals(1,reviewList.size());
    }

    @Test
    @DisplayName("Review - Respository : Save method test")
    public void reviewRepositorySaveMethodTest(){
        LocalDateTime presentTime=LocalDateTime.now();
        String reviewBody="Review Sample";
        reviewRepository.save(new Review(reviewBody,presentTime,presentTime));
    }

    @Test
    @DisplayName("Review - Repository : Get method Test")
    public void reviewRepositoryGetMethodTest(){
        String id="67e14811f46fe16991bbe90d";
        List<Review> reviewList=reviewRepository.get(id);
        assertEquals(1,reviewList.size());
        assertEquals("Review Sample",reviewList.get(0).getBody());
    }

    @Test
    @DisplayName("Review - Repository : Delete method Test")
    public void reviewRepositoryDeleteMethodTest(){
        String id="67e14811f46fe16991bbe90d";
        reviewRepository.delete(id);
        List<Review> reviewList=reviewRepository.get(id);
        assertEquals(0,reviewList.size());
    }
}
