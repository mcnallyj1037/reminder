package com.edwardjones.reminder.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.edwardjones.reminder.dao.ReminderPollingDao;
import com.edwardjones.reminder.domain.StickyNote;
import com.twilio.rest.lookups.v1.PhoneNumber;

@Component
public class ReminderPollingService {

	private static Log log = LogFactory.getLog(ReminderPollingService.class);
	
	@Autowired
	TextMessagingService textMessagingService;
	
	@Autowired
	ReminderPollingDao reminderPollingDao;
	
	/**
	 * Run infinite loop where the STICKY_NOTE table will be polled once every 60 seconds / 1 minute for processing.
	 * @throws InterruptedException
	 */
    public void pollStickyNoteTableForReminders() throws InterruptedException {
    	log.info("Inside pollReminderStaging() in ReminderPollingService.  Polling STICKY_NOTES table ever 1 minute.");
        boolean smsResult = false;
        
        //Infinite loop with 1 minute sleep time at end of each iteration.
    	while(true) {
      		List<StickyNote> stickyNoteList = reminderPollingDao.retrieveAllStickyNotes(new java.sql.Timestamp(new java.util.Date().getTime()));
         	log.info("Total number of reminders needing to be sent: " + stickyNoteList.size());
         	
         	if(!stickyNoteList.isEmpty()) {
         		for(int i = 0; i < stickyNoteList.size(); i++) {
             		try {
             		    if(!StringUtils.isEmpty(stickyNoteList.get(i).getPhone())) {
             		    	String formattedPhoneNumber = getFormattedPhoneNumber(stickyNoteList.get(i).getPhone());
             		    	smsResult = validatePhoneAndSendSMS(formattedPhoneNumber, stickyNoteList.get(i).getUniqueKey(), stickyNoteList.get(i).getTitle());
             			
             		    }else if(!StringUtils.isEmpty(stickyNoteList.get(i).getEmail())){
             			    log.info("Sending email to: " + stickyNoteList.get(i).getEmail());
             			    smsResult = true;
             		    }
             		    
             		    if(smsResult) {
             			    reminderPollingDao.updateReminderToBeEmpty(stickyNoteList.get(i).getUniqueKey());
             		    }
             		    
             		}catch(Exception e) {
             			log.info(e.getLocalizedMessage());
             		}
             	}
         	}
         	Thread.sleep(60000);
    	}
	}
    
    /**
     * Format the phone number for Twilio SMS messaging.
     * @param phoneNumber
     * @return
     */
    private String getFormattedPhoneNumber(String phoneNumber) {
    	String phoneNumberPrefix = "";
    	if(phoneNumber.startsWith("1")) {
		    phoneNumberPrefix = "+";
    	}else {
    		phoneNumberPrefix = "+1";
		}
    	return phoneNumberPrefix + phoneNumber;
    }
    
    /**
     * Leverage Twilio Lookups API to determine if phone number exists. If exist, send text message.
     * If it doesn't, an ApiException will be thrown immediately and caught with ControllerAdvice Global Exception handler.
     * If number not found, update STICY_NOTES table 'REMINDER_DATE' column to '0000-00-00 00:00:00'
     * @param lookupPhoneNumber
     * @throws com.twilio.exception.ApiException
     */
    private boolean validatePhoneAndSendSMS(String lookupPhoneNumber, String uniqueKey, String SmsMessage) throws com.twilio.exception.ApiException{
    	log.info("Looking up phone number...");
    	boolean smsResult = false;
    	try {
    		PhoneNumber number = PhoneNumber
	               .fetcher(new com.twilio.type.PhoneNumber(lookupPhoneNumber))
	               .fetch();
    	    
    	    log.info("Phone Country Code: " + number.getCountryCode() + 
		    			", Caller Info: " + number.getCallerName().toString() + 
		    			", Carrier Info: " + number.getCarrier().toString());
    	    
    	    smsResult = textMessagingService.sendTextMessage(lookupPhoneNumber, SmsMessage);
    	    
    	} catch(com.twilio.exception.ApiException e) {
            if(e.getStatusCode() == 404) {
                log.info("Phone number not found.");
                reminderPollingDao.updateReminderToBeEmpty(uniqueKey);

            } else {
                log.info(e.getLocalizedMessage());
                reminderPollingDao.updateReminderToBeEmpty(uniqueKey);
            }
        }
    	return smsResult;
    }
    
    
}
