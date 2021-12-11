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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class StickyNotesController {
	
	private static Log log = LogFactory.getLog(StickyNotesController.class);
	
	@Autowired
	StickyNotesService stickyNotesService;
	
	/**
	 * Create a single sticky note.
	 * @param uniqueKey
	 * @param title
	 * @param description
	 * @param reminderDate
	 * @param phone
	 * @param email
	 */
	@PutMapping("/stickynote/create")
    @Operation(summary = "create a sticky note", description = "creates a new sticky note")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Error")})
	public void createStickyNote(
				@Parameter(description ="Unique Key", required = true)
				@RequestParam(value = "uniqueKey", required = true) String uniqueKey,
				
				@Parameter(description ="Title", required = false)
				@RequestParam(value = "title", required = false) String title,
				
				@Parameter(description ="Description", required = false)
				@RequestParam(value = "description", required = false) String description,
				
				@Parameter(description ="Reminder Date", required = false)
				@RequestParam(value = "reminderDate", required = false) String reminderDate,
				
				@Parameter(description ="Phone number", required = false)
				@RequestParam(value = "phone", required = false) String phone, 
				
				@Parameter(description ="email address", required = false)
				@RequestParam(value = "email", required = false) String email) {
		try {
			log.info("Inside /stickynote/create endpoint controller.");
			log.info("uniqueKey=" + uniqueKey);
			log.info("title=" + title);
			log.info("description=" + description);
			log.info("reminderDate=" + reminderDate);
			log.info("phone=" + phone);
			log.info("email=" + email);
		    stickyNotesService.createStickyNote(uniqueKey,title,description, reminderDate, phone, email);
		    
		} catch(Exception e) {
			log.error(e.getLocalizedMessage(), e);
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
	public List<StickyNote> retrieveStickyNote(@PathVariable(value = "uniqueKey", required = true) String uniqueKey) {
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
	public void deleteStickyNote(@PathVariable(value = "stickyNoteId", required = true) String stickyNoteId) {
		try {
			log.info("Inside /stickynote/delete/{stickyNoteId} endpoint controller.");
		    stickyNotesService.deleteStickyNote(stickyNoteId);
		
	    } catch(Exception e) {
		    log.info(e.getLocalizedMessage());
	    }
	}
	
}
