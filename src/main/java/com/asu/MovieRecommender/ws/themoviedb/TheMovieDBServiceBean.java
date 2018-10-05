package com.asu.MovieRecommender.ws.themoviedb;

import org.json.simple.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.asu.MovieRecommender.utility.ApiUrl;
import com.asu.MovieRecommender.utility.Constants;

/**
 * @author leharbhatt
 *
 */
@Service
public class TheMovieDBServiceBean implements TheMovieDBService {

	private final RestTemplate restTemplate;
	private String language = "en-US";
	private Integer page = 1;

	public TheMovieDBServiceBean(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();

	}

	/**
	 * @return JSONObject This method invokes the movieDb api to get 20 now playing
	 *         movies and returns the response to the user.
	 */
	@Override
	public JSONObject getNowPlayingMovies() {

		ApiUrl apiUrl = new ApiUrl(Constants.NOWPLAYING);

		apiUrl.addkey();
		apiUrl.addLanguage(language);
		apiUrl.addPage(page);

		MoviesList response = null;

		try {
			response = restTemplate.getForObject(
					"https://api.internationalshowtimes.com/v4/movies/?cinema_id=40849&apikey=yuUdRKK7dyGG1bjABTFDSbHjV07MERxZ",
					MoviesList.class);

			for (int i = 0; i < response.getMovies().size(); i++) {
				System.out.println(response.getMovies().get(i).getId());
			}
			for (int i = 0; i < Constants.NO_OF_MOVIES; i++) {
				int movieId = Movie.getNowPlayingMovies().get(i);
				String url = "https://api.internationalshowtimes.com/v4/showtimes?apikey=yuUdRKK7dyGG1bjABTFDSbHjV07MERxZ&city_ids=1901&movie_id="
						+ Movie.getNowPlayingMovies().get(i);
				ShowtimesList showtimes = restTemplate.getForObject(url, ShowtimesList.class);
				Movie movie = Movie.getMovieMap().get(movieId);
				System.out.println(movie);
				movie.setAllTheatresShowtime(showtimes);
			}

		} catch (RestClientException e) {

			e.printStackTrace();
		}
		return new JSONObject();
	}
}
