package com.asu.MovieRecommender.ws.themoviedb;

import org.springframework.http.ResponseEntity;
import com.asu.MovieRecommender.Exceptions.MovieDetailsException;

/**
 * Interface to get now playing movies
 * @author leharbhatt
 *
 */

public interface TheMovieDBService {

	public ResponseEntity<ShowtimesList> getMovieShowtimes(String movieId) throws MovieDetailsException;

	ResponseEntity<MoviesList> getNowPlayingMoviesTheMovieDB() throws MovieDetailsException;

	ResponseEntity<TrailersJSON> getNowPlayingMoviesTrailers() throws MovieDetailsException;
}
