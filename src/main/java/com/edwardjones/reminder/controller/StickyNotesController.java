package com.edwardjones.reminder.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edwardjones.reminder.domain.StickyNote;
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
	public void createStickyNote() {
		stickyNotesService.createStickyNote();
	}
	@GetMapping("/stickynote/retrieve/{uniqueKey}")
	public List<StickyNote> retrieveStickyNote(
				@PathVariable(
					value = "uniqueKey",
					required = true
				)
				String uniqueKey
			) {
		return stickyNotesService.retrieveStickyNote(uniqueKey);
	}
	@DeleteMapping("/stickynote/delete")
	public void deleteStickyNote() {
		stickyNotesService.deleteStickyNote();
	}
	
}
