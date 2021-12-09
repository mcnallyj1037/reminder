package com.edwardjones.reminder.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edwardjones.reminder.dao.ReminderPollingDao;
import com.edwardjones.reminder.domain.StickyNote;

@Component
public class ReminderPollingService {

	private static Log log = LogFactory.getLog(ReminderPollingService.class);
	
	@Autowired
	TextMessagingService textMessagingService;
	
	@Autowired
	ReminderPollingDao reminderPollingDao;
	
    public void pollReminderStaging() {
 		
    	log.info("Inside pollReminderStaging() in ReminderPollingService");
    	List<StickyNote> stickyNoteList = reminderPollingDao.retrieveAllStickyNotes();
    	
		//Poll
		//Process each
    	//textMessagingService.sendTextMessage("+16039183678", "Hello!  This is a test.  A cool one.");
		
	}
}
