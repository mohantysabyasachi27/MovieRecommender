package com.asu.MovieRecommender.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController 
{
//    @Autowired
//	MovieRecommenderConfig movieRecommenderConfig ;
	@RequestMapping("/login/{id}")
	public String loginGoogle() throws Exception {
     return "welcome";	
    }
	@RequestMapping(method=RequestMethod.GET,value="/")  
	public String postLoginGoogle() throws Exception {
		   return "welcome";	
		    }

	@RequestMapping(method=RequestMethod.GET,value="/oauth2")  
	public String LoginGoogle() throws Exception {
		   return "welcome to google login";	
		    }
	
}
