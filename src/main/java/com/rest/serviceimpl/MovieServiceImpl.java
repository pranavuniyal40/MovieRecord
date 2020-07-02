package com.rest.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.Service.MovieService;
import com.rest.dao.MovieDAO;
import com.rest.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieDAO movieDao;

	@Override
	public List<Movie> getAllMovies() {

		List<Movie> movies = new ArrayList<Movie>();
		movieDao.findAll().forEach(movies::add);
		return movies;
	}

	@Override
	public Optional<Movie> getMovieById(Integer id) {

		Optional<Movie> movieData = movieDao.findById(id);
		return movieData;
	}

	@Override
	public void createMovie(Movie movie) {

		Movie movieData = movieDao.save(new Movie(movie.getTitle(), movie.getCategory(), movie.getStarRating()));

	}

	@Override
	public Movie updateMovie(Movie movie) {

		Movie getMovieData = new Movie();

		getMovieData.setTitle(movie.getTitle());

		getMovieData.setCategory(movie.getCategory());

		getMovieData.setStarRating(movie.getStarRating());

		return movieDao.save(getMovieData);
	}

	@Override
	public void deleteMovie(Integer id) {

		movieDao.deleteById(id);

	}

}
