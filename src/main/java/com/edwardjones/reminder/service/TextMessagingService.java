package com.edwardjones.reminder.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.edwardjones.reminder.controller.ReminderPollingController;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Component
public class TextMessagingService {
	
	private static Log log = LogFactory.getLog(ReminderPollingController.class);
	
	@Value("${twilio.account.sid}")
	private String twilioAcctSid;
	
	@Value("${twilio.account.auth.token}")
	private String twilioAcctAuthToken;
	
	@Value("${twilio.account.phone}")
	private String twilioAcctPhone;
	
	/**
	 * Send text message to a given phone number.
	 * @param toPhoneNumber
	 * @param textMessage
	 * @return boolean
	 */
	public boolean sendTextMessage(String toPhoneNumber, String textMessage){
		boolean isProcessedSuccessfully = false;
		log.info("Texing Phone: " + toPhoneNumber + ", the message: " + textMessage);

		//Twilio - Send Message
		Twilio.init(twilioAcctSid, twilioAcctAuthToken);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(toPhoneNumber),
                new com.twilio.type.PhoneNumber(twilioAcctPhone),
                textMessage)
            .create();
        
        isProcessedSuccessfully = true;  //This needs to go.  Use the response from API to determine true or false.  
        return isProcessedSuccessfully;
	}
	
	
}
