package com.edwardjones.reminder.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edwardjones.reminder.exception.ReminderException;
import com.edwardjones.reminder.service.ReminderPollingService;

@RestController
public class ReminderPollingController {

	private static Log log = LogFactory.getLog(ReminderPollingController.class);
	
	@Autowired
	ReminderPollingService reminderPollingService;
	
	/**
	 * Polling controller to poll Mongo DB Reminder staging table and process SMS messaging for reminder funcationality.
	 * @param session
	 */
	@GetMapping("/poll-reminders")
	public void pollReminders(HttpSession session) {
		log.info("Inside pollReminders() controller.");
		try {
			reminderPollingService.pollReminderStaging();
			
		} catch (ReminderException re) {
			re.printStackTrace();
			log.info(re.getLocalizedMessage());
		}
	}
	
}
