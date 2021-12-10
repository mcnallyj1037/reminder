package com.edwardjones.reminder.controller;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	private static Log log = LogFactory.getLog(StickyNotesController.class);
	
	@Autowired
	StickyNotesService stickyNotesService;
	
	/**
	 * Create a single sticky note.
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
				String reminderDate,
				
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
		try {
			log.info("Inside /stickynote/create endpoint controller.");
		    stickyNotesService.createStickyNote(uniqueKey,title,description, reminderDate, phone, email);
		    
		} catch(Exception e) {
			log.info(e.getLocalizedMessage());
		}
	}

	/**
	 * Retrieve all sticky notes by ID.
	 * @param uniqueKey
	 * @return
	 */
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
		List<StickyNote> stickyNoteList = null;
		try {
			log.info("Inside /stickynote/retrieve/{uniqueKey} endpoint controller.");
		    stickyNoteList = stickyNotesService.retrieveStickyNote(uniqueKey);
		    
		} catch(Exception e) {
			log.info(e.getLocalizedMessage());
		}
		return stickyNoteList;
	}

	/**
	 * Delete a single sticky note controller.
	 * @param stickyNoteId
	 */
	@DeleteMapping("/stickynote/delete/{stickyNoteId}")
	public void deleteStickyNote(
				@PathVariable(
					value = "stickyNoteId",
					required = true
				)
				String stickyNoteId
			) {
		try {
			log.info("Inside /stickynote/delete/{stickyNoteId} endpoint controller.");
		    stickyNotesService.deleteStickyNote(stickyNoteId);
		
	    } catch(Exception e) {
		    log.info(e.getLocalizedMessage());
	    }
	}
	
}
