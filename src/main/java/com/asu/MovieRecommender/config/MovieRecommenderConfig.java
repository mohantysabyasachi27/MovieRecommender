package com.asu.MovieRecommender.config;

/**@Configuration
@EnableOAuth2Sso**/
public class MovieRecommenderConfig  {
	/** 
	@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .csrf()
	                    .disable()
	                .antMatcher("/**")
	                .authorizeRequests()
	                .antMatchers("/index.html")
	                    .permitAll()
	                .anyRequest()
	                    .authenticated();
	    }
	    **/

}
