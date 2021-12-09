package com.edwardjones.reminder.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.edwardjones.reminder.dao.ReminderPollingDao;
import com.edwardjones.reminder.domain.StickyNote;
import com.edwardjones.reminder.exception.ReminderException;

@Component
public class ReminderPollingService {

	private static Log log = LogFactory.getLog(ReminderPollingService.class);
	
	@Autowired
	TextMessagingService textMessagingService;
	
	@Autowired
	ReminderPollingDao reminderPollingDao;
	
    public void pollReminderStaging() throws ReminderException {
    	log.info("Inside pollReminderStaging() in ReminderPollingService.  Starting TimerTask.  Runs once every 1 minute.");

    	java.util.TimerTask task = new java.util.TimerTask() {
            int prevCount = 0;
            boolean smsResult = false;
            
            @Override
            public void run() {
                try {
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
                   
                } catch (Exception e) {
                    log.info(e.getLocalizedMessage());
                }
            }
        };
        java.util.Timer timer = new java.util.Timer(true);// true to run timer as daemon thread
        timer.schedule(task, 0, 10000);// Run task every 5 second
        try {
            Thread.sleep(60000); // Cancel task after 1 minute.
            
        } catch (InterruptedException e) {
            log.info(e.getLocalizedMessage());
        }
        timer.cancel();	
	}
    
    
}
