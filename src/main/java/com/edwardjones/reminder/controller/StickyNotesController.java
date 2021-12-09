package com.edwardjones.reminder.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edwardjones.reminder.service.StickyNotesService;

@RestController
public class StickyNotesController {

	private static Log log = LogFactory.getLog(ReminderPollingController.class);
	
	@Autowired
	StickyNotesService stickyNotesService;
	
	/**
	 * Polling controller to poll Mongo DB Reminder staging table and process SMS messaging for reminder funcationality.
	 * @param session
	 */
	@PutMapping("/stickynote/create")
	public void createStickyNote(int id, String uniqueKey, String title, String description, java.sql.Timestamp reminderDate, String phone, String email, java.sql.Timestamp dateCreated) {
		stickyNotesService.createStickyNote(id, uniqueKey,title,description, reminderDate, phone, email, dateCreated);
	}
	@GetMapping("/stickynote/retrieve")
	public void retrieveStickyNote() {
		stickyNotesService.retrieveStickyNote();
	}
	@DeleteMapping("/stickynote/delete")
	public void deleteStickyNote(String stickyNoteId) {
		stickyNotesService.deleteStickyNote(stickyNoteId);
	}
	
}
