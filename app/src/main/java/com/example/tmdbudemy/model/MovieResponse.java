package com.example.tmdbudemy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class MovieResponse{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<Movie> movies;

	@SerializedName("total_results")
	private int totalmovies;

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setmovies(List<Movie> movies){
		this.movies = movies;
	}

	public List<Movie> getmovies(){
		return movies;
	}

	public void setTotalmovies(int totalmovies){
		this.totalmovies = totalmovies;
	}

	public int getTotalmovies(){
		return totalmovies;
	}

	@Override
 	public String toString(){
		return 
			"MovieResponse{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",movies = '" + movies + '\'' +
			",total_movies = '" + totalmovies + '\'' +
			"}";
		}
}