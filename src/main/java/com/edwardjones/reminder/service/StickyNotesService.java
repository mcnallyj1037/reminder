package com.edwardjones.reminder.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edwardjones.reminder.dao.StickyNoteDao;
import com.edwardjones.reminder.domain.StickyNote;

@Component
public class StickyNotesService {

	private static Log log = LogFactory.getLog(StickyNotesService.class);

	@Autowired
	StickyNoteDao stickyNoteDao;

	/**
	 * Create a sticky note service method.
	 * @param uniqueKey
	 * @param title
	 * @param description
	 * @param reminderDate
	 * @param phone
	 * @param email
	 */
	public void createStickyNote(String uniqueKey, String title, String description, java.sql.Timestamp reminderDate, String phone, String email) {
		// TODO we need to generate the id query max id and add 1 to get new ID
		Integer stickyNoteRecord = stickyNoteDao.insertStickyNote(uniqueKey,title,description, reminderDate, phone, email);
		log.info("Service: Inserted stickyNoteRecord."+stickyNoteRecord);
	}

	/**
	 * Retrieve all sticky notes for unique key
	 * @param uniqueKey
	 * @return
	 */
	public List<StickyNote> retrieveStickyNote(String uniqueKey) {
		return stickyNoteDao.retrieveStickyNotesByUniqueKey(uniqueKey);
	}

	/**
	 * Delete a single sticky note by sticky note ID.
	 * @param stickyNoteId
	 */
    public void deleteStickyNote(String stickyNoteId) {
		Integer stickyNoteRecord = stickyNoteDao.deleteStickyNote(stickyNoteId);
    	log.info("Service: Deleted stickyNoteRecord."+stickyNoteRecord);
    }
}
