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
	
    public void pollStickyNoteTableForReminders() throws InterruptedException {
    	log.info("Inside pollReminderStaging() in ReminderPollingService.  Polling STICKY_NOTES table ever 1 minute.");
        boolean smsResult = false;
        
        //Infinite loop with 1 minute sleep time on each iteration.
    	while(true) {
    		Thread.sleep(60000);
    		
    		List<StickyNote> stickyNoteList = reminderPollingDao.retrieveAllStickyNotes(new java.sql.Timestamp(new java.util.Date().getTime()));
         	log.info("Total number of reminders needing to be sent: " + stickyNoteList.size());
         	
         	for(int i = 0; i < stickyNoteList.size(); i++) {
         		try {
         		    if(!StringUtils.isEmpty(stickyNoteList.get(i).getPhone())) {
         			    if(stickyNoteList.get(i).getPhone().startsWith("1")) {
             			    smsResult = textMessagingService.sendTextMessage("+" + stickyNoteList.get(i).getPhone(), stickyNoteList.get(i).getTitle());
             			
             		    }else {
             			    smsResult = textMessagingService.sendTextMessage("+1" + stickyNoteList.get(i).getPhone(), stickyNoteList.get(i).getTitle());
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
    	

    	
	}
    
    
}
