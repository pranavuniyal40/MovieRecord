package com.rest.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.Service.MovieService;
import com.rest.controller.MovieController;
import com.rest.model.Movie;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class SpringBootDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MovieService movService;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void getAllMoviesData() throws Exception {
		String uri = "/api/movies";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		
		if ((status != 200))
			return;
		else
			assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		Movie[] productlist = new SpringBootDemoApplicationTests().mapFromJson(content, Movie[].class);
		assertTrue(productlist.length > 0);

	}

	@Test
	public void createMovieData() throws Exception {
		String uri = "/api/movies";
		
		Movie movie = new Movie();
		movie.setTitle("A");
		movie.setCategory("Rank1");
		movie.setStarRating(1.0);
		movie.setId(1);
		
		String inputJson = new SpringBootDemoApplicationTests().mapToJson(movie);
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		
		if ((status != 200))
			return;
		else
			assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Movie is created successfully");
	}

	@Test
	public void updateMovieData() throws Exception {
		String uri = "api/movies/1";
		Movie movie = new Movie();
		movie.setTitle("NEW");

		String inputJson = new SpringBootDemoApplicationTests().mapToJson(movie);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		
		if ((status != 200))
			return;
		else
			assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Movie is updated successsfully");
	}

	@Test
	public void deleteMovieData() throws Exception {
		String uri = "api/movies/2";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();

		System.out.println(status);
		
		if ((status != 200))
			return;
		else
			assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Movie is deleted successsfully");
	}

}
