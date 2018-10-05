package com.asu.MovieRecommender.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.asu.MovieRecommender.UserService.UserAuthenticationFailureHandler;
import com.asu.MovieRecommender.UserService.UserAuthenticationSuccessHandler;
import com.asu.MovieRecommender.UserService.UserGoogleAuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @author Sabyasachi Mohanty & Kumar Prabhu Kalyan
	 * 
	 * @since Sept28, Sprint-1
	 * 
	 * @Task Integrate Spring Security
	 */

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordencoder;

	@Autowired
	private UserGoogleAuthenticationSuccessHandler userGoogleAuthenticationHandler;
	@Autowired
	private UserAuthenticationFailureHandler userAuthenticationFailureHandler;
	@Autowired
	private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors();
		http.authorizeRequests().antMatchers("/login/**").authenticated().anyRequest().permitAll().and().formLogin()
				.failureUrl("/login?error").failureHandler(userAuthenticationFailureHandler)
				.successHandler(userAuthenticationSuccessHandler)/* .loginPage("/movieloginpage.html") */
				.permitAll().and().oauth2Login().successHandler(userGoogleAuthenticationHandler)
				.and().logout().logoutSuccessUrl("/oauth2");

		/*
		 * http .authorizeRequests() .antMatchers("/login*").anonymous()
		 * .anyRequest().authenticated() .and() .formLogin() .loginPage("/login.html")
		 * .defaultSuccessUrl("/homepage.html") .failureUrl("/login.html?error=true")
		 * .and() .logout().logoutSuccessUrl("/login.html");
		 */
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
