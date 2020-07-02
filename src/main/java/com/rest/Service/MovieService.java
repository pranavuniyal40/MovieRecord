package com.rest.Service;

import java.util.List;
import java.util.Optional;

import com.rest.model.Movie;

public interface MovieService {

	List<Movie> getAllMovies();

	Optional<Movie> getMovieById(Integer id);

	void createMovie(Movie movie);

	Movie updateMovie(Movie movie, Integer id);

	void deleteMovie(Integer id);

}
