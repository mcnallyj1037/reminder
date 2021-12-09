package com.edwardjones.reminder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edwardjones.reminder.service.ReminderPollingService;

public class AppStartupBean {
    
	private static Log log = LogFactory.getLog(AppStartupBean.class);
	
	@Autowired
	ReminderPollingService reminderPollingService;
	
	/**
	 * Method will run on app startup.
	 */
	public void init() {
        log.info("Application started.  Initializing...");
        try {
			reminderPollingService.pollStickyNoteTableForReminders();
			
		} catch (InterruptedException e) {
			log.info(e.getLocalizedMessage());
			
		} catch(Exception e) {
			log.info(e.getLocalizedMessage());
		}
    }
}
