package org.pjadm.movie_review;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import org.pjadm.movie_review.document.Movie;
import org.pjadm.movie_review.repository.MovieRepository;
import org.pjadm.movie_review.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
		System.out.println(movieList);
		assertNotNull(movieList);
		assertEquals(10,movieList.size());
	}


	@Test
	@DisplayName("Movie - Service : FindAll method Test")
	void movieServiceFindAllTest(){
		List<Movie> movieList=movieService.getAllMovies();
		System.out.println(movieList);
		assertNotNull(movieList);
		assertEquals(10,movieList.size());
	}


	@Test
	@DisplayName("Movie - Controller : GetAll Method Test")
	void movieControllerGetAllTest() throws Exception {
		mockMvc.perform(get("/movie/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(10)));
	}


}
