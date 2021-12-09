package com.edwardjones.reminder.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edwardjones.reminder.domain.StickyNote;
import com.edwardjones.reminder.service.StickyNotesService;

@RestController
public class StickyNotesController {
	
	@Autowired
	StickyNotesService stickyNotesService;
	
	/**
	 * Polling controller to poll Mongo DB Reminder staging table and process SMS messaging for reminder funcationality.
	 * @param session
	 */
	@PutMapping("/stickynote/create")
	public void createStickyNote(
				@RequestParam(
					value = "uniqueKey",
					required = true
				)
				String uniqueKey,
				
				@RequestParam(
					value = "title",
					required = false
				)
				String title,
				
				@RequestParam(
					value = "description",
					required = false
				)
				String description,
				
				@RequestParam(
					value = "reminderDate",
					required = false
				)
				java.sql.Timestamp reminderDate,
				
				@RequestParam(
					value = "phone",
					required = false
				)
				String phone, 
				
				@RequestParam(
					value = "email",
					required = false
				)
				String email
			){
		stickyNotesService.createStickyNote(uniqueKey,title,description, reminderDate, phone, email);
	}

	@GetMapping(
		path = "/stickynote/retrieve/{uniqueKey}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public List<StickyNote> retrieveStickyNote(
				@PathVariable(
					value = "uniqueKey",
					required = true
				)
				String uniqueKey
			) {
		return stickyNotesService.retrieveStickyNote(uniqueKey);
	}

	@DeleteMapping("/stickynote/delete/{stickyNoteId}")
	public void deleteStickyNote(
				@PathVariable(
					value = "stickyNoteId",
					required = true
				)
				String stickyNoteId
			) {
		stickyNotesService.deleteStickyNote(stickyNoteId);
	}
	
}
