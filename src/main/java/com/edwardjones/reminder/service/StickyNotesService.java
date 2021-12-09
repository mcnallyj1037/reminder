package com.edwardjones.reminder.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edwardjones.reminder.dao.ReminderPollingDao;
import com.edwardjones.reminder.domain.StickyNote;

@Component
public class StickyNotesService {

	private static Log log = LogFactory.getLog(StickyNotesService.class);
	
	@Autowired
	ReminderPollingDao dao;

	public void createStickyNote() {
	}

	public List<StickyNote> retrieveStickyNote(String uniqueKey) {
		return dao.retrieveStickyNotes(uniqueKey);
	}

    public void deleteStickyNote() {
    }
	

	
   
}
