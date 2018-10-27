package com.asu.MovieRecommender.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailBuilder {
	private TemplateEngine templateEngine;

	@Autowired
	    public MailBuilder(TemplateEngine templateEngine) {
	        this.templateEngine = templateEngine;
	    }

	public String build(String firstName,String message) {
		Context context = new Context();
		context.setVariable("message", message);
		context.setVariable("salutation", firstName);
		context.setVariable("thankText", "MovieRecommenderTeam");
		return templateEngine.process("mailTemplate", context);
	}

}
