package com.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	private String category;
	
	@DecimalMax("5.0") @DecimalMin("0.5") 
	private double starRating;
	
	
	public Movie() {

	}

	public Movie(String title, String category, double starRating) {
		this.title = title;
		this.category = category;
		this.starRating = starRating;
	}

	public Integer getId() {
		return id;
		
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getStarRating() {
		
		return starRating;
		
	}

	public void setStarRating(double starRating) {
		this.starRating = starRating;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", category=" + category + ", starRating=" + starRating + "]";
	}

}
