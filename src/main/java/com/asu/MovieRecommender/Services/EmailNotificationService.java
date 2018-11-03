package com.asu.MovieRecommender.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.asu.MovieRecommender.User.User;

@Service
public class EmailNotificationService {

	@Autowired
	private MailBuilder mailBuilder;

	private JavaMailSender mailSender;

	@Autowired
	public EmailNotificationService(JavaMailSender mailSender) {
		this.mailSender = mailSender;

	}

	public String sendMail(User user) throws Exception {
		MimeMessagePreparator message = messageSender -> {
			MimeMessageHelper helper = new MimeMessageHelper(messageSender);
			helper.setTo(user.getUserEmailId());
			helper.setFrom("kmrprabhu93@gmail.com");
			helper.setSubject("Registration Success");
			// message.setText(content, true);
			String content = mailBuilder.build(user.getFirstName(), "Thank You for registering");
			helper.setText(content, true);
			
		};

		try {
			mailSender.send(message);
			return null;
		} catch (MailException m) {
			throw new Exception(m.getMessage());
		}

	}

}
