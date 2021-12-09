package com.edwardjones.reminder.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.edwardjones.reminder.domain.StickyNote;
import com.edwardjones.reminder.domain.StickyNoteMapper;

@Component
public class StickyNoteDao {

	private static Log log = LogFactory.getLog(StickyNoteDao.class);

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	//java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	private static final String insertIntoSticky = "INSERT INTO stickynotes_db.sticky_notes " + 
			                                             "(id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED) " + 
			                                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String selectStickyNotes = "SELECT id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED FROM stickynotes_db.sticky_notes";
	
	private static final String deleteStickyNotes = "DELETE FROM stickynotes_db.sticky_notes WHERE ID = ?" ;
	
	private static final String selectStickyNotesByUniqueKey = "SELECT id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED FROM stickynotes_db.sticky_notes WHERE UNIQUE_KEY = ? ";
	
	
	 public List<StickyNote> retrieveAllStickyNotes() {
	    	List<StickyNote> stickyNoteList = null;
	    	try {
	    		stickyNoteList = this.jdbcTemplate.query(selectStickyNotes, new StickyNoteMapper(), new Object[]{});
	    		log.info("Successfully retrieved user from USERS table." + stickyNoteList.size());
	    		
	    	}catch(Exception e) {
	    		log.info(e.getLocalizedMessage());
	    	}
	    	return stickyNoteList;
	}
	
	
	@Transactional
	public int insertStickyNote(int id, String uniqueKey, String title, String description, java.sql.Timestamp reminderDate, String phone, String email, java.sql.Timestamp dateCreated) {
		log.info("Entered insertStickyNote() StickyNoteDao.");
		int result = 0;
		try {
			
			result = this.jdbcTemplate.update(insertIntoSticky);
			
			log.info("Successfully saved stickyNotes details.");
				    
	    }catch(Exception e) {
    	    log.info(e.getLocalizedMessage());
    	}   	    
	    return result;
	}

	@Transactional
	public int deleteStickyNote(String stickyNoteId) {
		log.info("Entered deleteStickyNote() StickyNoteDao.");
		int result = 0;
		try {
			
			result = this.jdbcTemplate.update(deleteStickyNotes ,stickyNoteId);
			
			log.info("Successfully deleted stickyNotes details.");
				    
	    }catch(Exception e) {
    	    log.info(e.getLocalizedMessage());
    	}   	    
	    return result;
	}
	
	public List<StickyNote> retrieveStickyNotesByUniqueKey(String uniqueKey) {
		List<StickyNote> stickyNotes = null;
			try {
				stickyNotes = this.jdbcTemplate.query(selectStickyNotesByUniqueKey, new StickyNoteMapper(), uniqueKey);
				log.info("Successfully retrieved StickyNotes from sticky_notes table. " + stickyNotes.size() + " for uniqueKey: " + uniqueKey);
	    	}
			catch(Exception e) {
				log.info(e.getLocalizedMessage());
			}
			return stickyNotes;
	}

}
