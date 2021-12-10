package com.edwardjones.reminder.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.edwardjones.reminder.dao.ReminderPollingDao;
import com.edwardjones.reminder.domain.StickyNote;

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
             			    if(stickyNoteList.get(i).getPhone().startsWith("1")) {
             			    	log.info("Sending text now...");
                 			    smsResult = textMessagingService.sendTextMessage("+" + stickyNoteList.get(i).getPhone(), stickyNoteList.get(i).getTitle());
                 			    //smsResult = true;
                 		    }else {
                 		    	log.info("Sending text now...");
                 			    smsResult = textMessagingService.sendTextMessage("+1" + stickyNoteList.get(i).getPhone(), stickyNoteList.get(i).getTitle());
                 		    	//smsResult = true;
                 		    }
             			    log.info("SMS has been sent: " + stickyNoteList.size());
             			
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
    
    
}
