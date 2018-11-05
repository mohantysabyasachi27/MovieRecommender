package com.asu.MovieRecommender.Services;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.asu.MovieRecommender.User.User;
import com.asu.MovieRecommender.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class EmailAndSMSAsyncService {

	public static Logger logger = LogManager.getLogger(EmailAndSMSAsyncService.class);
	@Autowired
	EmailNotificationService emailNotific;
	@Autowired
	TwilioConfig twilioConfig;
	
	@Async("mailSmsExecutor")
	public  CompletableFuture<Void> async(User user) {
	    try {
	        String intermediate = sendSMS(user.getUserContactNo(),"You have successfully registered to Movierecommeder App");
	    	//sendSMS(user.getUserContactNo(), "OTP-"+sendOtp(user.getUserName()).toString());
	        String result = emailNotific.sendMail(user);
	        
	    } catch (Throwable t) {
	        logger.debug(t.getMessage());
	    }
		return CompletableFuture.completedFuture(null);
	   
	}

	/*
	private Integer sendOtp(String userName)
	{
		Random rand  = new Random();
		int otp =1000+rand.nextInt(8999);
		hashOperations.put("OTP",userName,otp);
		return otp;
	}*/
	
	
	
	private String sendSMS(String contactNo,String smsMessage)
	{
		//System.out.println("SID"+twilioConfig.getAccountSID()+"Auth"+twilioConfig.getAuthToken());
		//System.out.println(twilioConfig.getPhoneNumber());
		 Twilio.init(twilioConfig.getAccountSID(), twilioConfig.getAuthToken());
		try {
		 Message message = Message.creator(new PhoneNumber(contactNo),
				 new PhoneNumber(twilioConfig.getPhoneNumber()), 
			        smsMessage).create();
		 return message.getSid();
		}catch(Exception e)
		{
			logger.debug(e.getMessage());
			//e.printStackTrace();
		}
		return contactNo;
		
		 

	}
}
