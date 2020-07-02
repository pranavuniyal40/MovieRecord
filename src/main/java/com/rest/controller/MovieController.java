package com.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Service.MovieService;
import com.rest.model.Movie;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;

	@GetMapping(value = "/movies")
	public ResponseEntity<List<Movie>> getAllMovies() {
		try {
			List<Movie> movies = movieService.getAllMovies();

			
			if (movies.isEmpty()) {
				return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
			}
			 

			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/movies/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") Integer id) {
		Optional<Movie> movieData = movieService.getMovieById(id);

		if (movieData.isPresent()) {

			return new ResponseEntity<Movie>(movieData.get(), HttpStatus.OK);

		} else {

			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/movies")
	public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
		try {

			movieService.createMovie(movie);

			return new ResponseEntity<Movie>(HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping(value = "/movies/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") Integer id, @RequestBody Movie movie) {
		Optional<Movie> movieData = movieService.getMovieById(id);

		if (movieData.isPresent()) {

			Movie updateMovieData = movieService.updateMovie(movie,id);

			return new ResponseEntity<Movie>(HttpStatus.OK);

		} else {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/movies/{id}")
	public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") Integer id) {
		try {

			movieService.deleteMovie(id);

			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
