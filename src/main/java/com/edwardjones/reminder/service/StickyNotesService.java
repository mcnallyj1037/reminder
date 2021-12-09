package com.edwardjones.reminder.service;


import java.time.Instant;
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

	public void createStickyNote(String uniqueKey, String title, String description, java.sql.Timestamp reminderDate, String phone, String email) {
		// TODO we need to generate the id query max id and add 1 to get new ID
		int id = 0;
		java.sql.Timestamp dateCreated = java.sql.Timestamp.from(Instant.now());
		Integer stickyNoteRecord = stickyNoteDao.insertStickyNote(id, uniqueKey,title,description, reminderDate, phone, email, dateCreated);
		log.info("Service: Inserted stickyNoteRecord."+stickyNoteRecord);
	}

	public List<StickyNote> retrieveStickyNote(String uniqueKey) {
		return stickyNoteDao.retrieveStickyNotesByUniqueKey(uniqueKey);
	}

    public void deleteStickyNote(String stickyNoteId) {
		Integer stickyNoteRecord = stickyNoteDao.deleteStickyNote(stickyNoteId);
    	log.info("Service: Deleted stickyNoteRecord."+stickyNoteRecord);
    }
}
