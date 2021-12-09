package com.edwardjones.reminder.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edwardjones.reminder.service.ReminderPollingService;

@RestController
public class ReminderPollingController {

	private static Log log = LogFactory.getLog(ReminderPollingController.class);
	
	@Autowired
	ReminderPollingService reminderPollingService;
	
	/**
	 * Polling controller to poll STICKY_NOTE table and process SMS messaging for reminder functionality.
	 * Needs to be executed on startup to run behind the scenes infinitely.
	 */
	@GetMapping("/poll-reminders")
	public void pollReminders() {
		log.info("Inside pollReminders() controller.");
		try {
			reminderPollingService.pollStickyNoteTableForReminders();
			
		}catch(Exception e) {
			log.info(e.getLocalizedMessage());
		}
	}
	
}
