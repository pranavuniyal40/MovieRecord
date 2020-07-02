package com.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.model.Movie;

@Repository
public interface MovieDAO extends CrudRepository<Movie, Integer>
{

}
