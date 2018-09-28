package com.asu.MovieRecommender;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * 
 * @author leharbhatt
 *
 */

@Configuration
public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    /*
     * This method sets up a username and password for the rest api authentication
     *  */
    public void init(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())  
       .withUser("movierecommender")
        .password("s3cur!T")
        .roles("USER","ADMIN","SYSADMIN");
    }
}