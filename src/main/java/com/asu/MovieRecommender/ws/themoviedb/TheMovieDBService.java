package com.asu.MovieRecommender.ws.themoviedb;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.asu.MovieRecommender.Exceptions.MovieDetailsException;

/**
 * Interface to get now playing movies
 * @author leharbhatt
 *
 */

public interface TheMovieDBService {

	public List<Showtimes> getMovieShowtimes(String movieName, String movieId) throws MovieDetailsException;

	ResponseEntity<MoviesList> getNowPlayingMoviesTheMovieDB(String type) throws MovieDetailsException;

	String getNowPlayingMoviesTrailers(String movieId) throws MovieDetailsException;

	ResponseEntity<CinemasList> getCinemas(String movieName, String movieId) throws MovieDetailsException;

	ResponseEntity<MoviesList> getRecommendedMovies(String movieId) throws MovieDetailsException;
}
