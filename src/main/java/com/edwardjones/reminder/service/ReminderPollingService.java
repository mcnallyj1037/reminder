package com.edwardjones.reminder.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReminderPollingService {

	private static Log log = LogFactory.getLog(ReminderPollingService.class);
	
	@Autowired
	TextMessagingService textMessagingService;
	
    public void pollReminderStaging() {
 		
		//Poll
		//Process each
    	textMessagingService.sendTextMessage("+16039183678", "Hello!  This is a test.  A cool one.");
		
	}
}
