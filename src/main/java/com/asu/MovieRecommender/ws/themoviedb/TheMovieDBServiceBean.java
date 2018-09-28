package com.asu.MovieRecommender.ws.themoviedb;

import java.net.URISyntaxException;
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

		JSONObject response = null;

		try {
			response = restTemplate.getForObject(apiUrl.buildUrl().toURI(), JSONObject.class);
			response.remove(Constants.DATE_ATTRIBUTE);
			response.remove(Constants.PAGE_ATTRIBUTE);
			response.remove(Constants.TOTAL_PAGES_ATTRIBUTE);
			response.remove(Constants.TOTAL_RESULTS_ATTRIBUTE);
			System.out.println("response: " + response);

		} catch (RestClientException e) {

			e.printStackTrace();
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		return response;
	}
}
