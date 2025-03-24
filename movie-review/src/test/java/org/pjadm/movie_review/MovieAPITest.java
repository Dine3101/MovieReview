package org.pjadm.movie_review;



import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.document.Review;
import org.pjadm.movie_review.dto.MovieResponseDTO;
import org.pjadm.movie_review.dto.ReviewRequestDTO;
import org.pjadm.movie_review.repository.MovieRepository;
import org.pjadm.movie_review.service.MovieService;
import org.pjadm.movie_review.util.MovieOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class MovieAPITest {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieService movieService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Movie - Repository : FindAll method Test")
	void movieRepositoryFindAllTest() {
		List<Movie> movieList=movieRepository.getAll();
		assertNotNull(movieList);
		assertEquals(10,movieList.size());
	}


	@Test
	@DisplayName("Movie - Service : FindAll method Test")
	void movieServiceFindAllTest(){
		List<Movie> movieList=movieService.getAllMovies();
		assertNotNull(movieList);
		assertEquals(10,movieList.size());
	}


	@Test
	@DisplayName("Movie - Controller : GetAll Method Test")
	void movieControllerGetAllTest() throws Exception {
		mockMvc.perform(get("/movie/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title",is("Puss in Boots: The Last Wish")));
	}

	@Test
	@DisplayName("Movie - Controller : GetMovieByImdbId Method Test")
	void movieControlletGetMovieByImdbIdTest() throws Exception{
		mockMvc.perform(get("/movie/tt3935174"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(0)));
	}

	@Test
	@DisplayName("Movie - Repository : Save Method Test")
	void movieRepositorySaveMethodTest(){
		movieRepository.save(MovieOperations.getSampleMovie());
		assertEquals("RRR - Tamil Dubbed",movieRepository.get("tt3935174").get(0).getTitle());
	}

	@Test
	@DisplayName("Movie - Service : AddMovie Method Test")
	void movieServiceAddMovieMethodTest(){
		movieService.addMovie(MovieOperations.getSampleMovieRequestDTO());
		assertEquals("RRR - Tamil Dubbed",movieRepository.get("tt3935174").get(0).getTitle());
	}

	@Test
	@DisplayName("Movie - Controller : PostMovie API Test")
	void movieControllerPostMovieMethodTest() throws Exception{
		mockMvc.perform(post("/movie")
				.content(MovieOperations.getSampleMovieRequestDTOJson())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		assertEquals("RRR - Tamil Dubbed",movieRepository.get("tt3935174").get(0).getTitle());
	}
	@Test
	@DisplayName("Movie - Repository : Delete Method Test")
	void movieRepositoryDeleteMethodTest(){
		movieRepository.delete("tt3935174");
		assertEquals(0,movieRepository.get("tt3935174").size());
	}

	@Test
	@DisplayName("Movie - Service : DeleteMovie Method Test")
	void movieServiceDeleteMovieMethodTest(){
		List<MovieResponseDTO> response = movieService.deleteMovie("tt3935174");
		assertEquals("RRR - Tamil Dubbed",response.get(0).getTitle());
		assertEquals(0,movieRepository.get("tt3935174").size());
	}

	@Test
	@DisplayName("Movie - Controller : DeleteMovie API Test")
	void movieControllerDeleteMovieMethodTest() throws Exception{
		mockMvc.perform(delete("/movie/tt3935174"))
				.andExpect(status().isOk());
		assertEquals(0,movieRepository.get("tt3935174").size());
	}

	@Test
	@DisplayName("Movie - Repository : AddReview method test")
	void movieRespositoryAddReviewMethodTest(){
		LocalDateTime currentTime=LocalDateTime.now();
		String reviewBody="Sample Review for Avatar movie";
		String imdbId="tt0499549";
		movieRepository.addReview(imdbId,new Review(reviewBody,currentTime,currentTime));
		List<Movie> movieList=movieRepository.get(imdbId);
		assertTrue(movieList.size()>0);
		List<Review> reviewList=movieList.get(0).getReviewIds();
		assertTrue(reviewList.size()>0);
	}

	@Test
	@DisplayName("Movie - Repository : GetReview Method test")
	void movieRepositoryGetReviewMethodTest(){
		String imdbId="tt0499549";
		List<Review> reviewList=movieRepository.get(imdbId).get(0).getReviewIds();
		System.out.println(reviewList);
		assertEquals(2,reviewList.size());
	}

	@Test
	@DisplayName("Movie - Service : AddReview Method Test")
	void movieServiceAddReviewMethodTest(){
		String reviewBody="Sample Review for Avatar movie - 1";
		String imdbId="tt0499549";
		movieService.addReview(imdbId,new ReviewRequestDTO(reviewBody));
	}

	@Test
	@DisplayName("Movie - Controller : AddReview API Test")
	void movieControllerAddMovieReviewTest() throws Exception {
		String imdbId="tt11116912";
		String jsonRequestBody=MovieOperations.getSampleReviewRequestDTOJson();
		mockMvc.perform(post("/movie/"+imdbId)
				.content(jsonRequestBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		List<Review> reviewList=movieRepository.get(imdbId).get(0).getReviewIds();
		assertEquals(1,reviewList.size());
		assertEquals("Sample Review for Troll Movie",reviewList.get(0).getBody());
	}

}
