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
	
	private static final String insertIntoSticky = "INSERT INTO stickynotes_db.sticky_notes " + 
			                                             "(id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED) " + 
			                                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String selectStickyNotes = "SELECT id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED FROM stickynotes_db.sticky_notes";
	
	private static final String deleteStickyNotes = "DELETE FROM stickynotes_db.sticky_notes WHERE ID = ?" ;
	
	private static final String selectStickyNotesByUniqueKey = "SELECT id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED FROM stickynotes_db.sticky_notes WHERE UNIQUE_KEY = ? ";
	
	private static final String selectStickyNoteMaxId = "SELECT MAX(id) FROM stickynotes_db.sticky_notes";
	
	/**
	 * Retrieve a list of all sticky notes from the STICKY_NOTES table.
	 * @return
	 */
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
	
	/**
	 * Insert a single Sticky Note record to the STICKY_NOTES table.
	 * @param uniqueKey
	 * @param title
	 * @param description
	 * @param reminderDate
	 * @param phone
	 * @param email
	 * @return
	 */
	@Transactional
	public int insertStickyNote(String uniqueKey, String title, String description, java.sql.Timestamp reminderDate, String phone, String email) {
		log.info("Entered insertStickyNote() StickyNoteDao.");
		int result = 0;
		int id = 0;
		try {
			//
			int selectStickyNoteMax =  this.jdbcTemplate.queryForObject(selectStickyNoteMaxId, Integer.class);
			if (selectStickyNoteMax>0){
			    id = selectStickyNoteMax+1;
			} else{
				id = 1;
			}
			result = this.jdbcTemplate.update(insertIntoSticky,id,uniqueKey, title, description, reminderDate, phone, email, new java.sql.Timestamp(new java.util.Date().getTime()));
			log.info("Successfully saved stickyNotes details.");
				    
	    }catch(Exception e) {
    	    log.info(e.getLocalizedMessage());
    	}   	    
	    return result;
	}

	/**
	 * Delete a single sticky note from STICKY_NOTE table using a unique id for the sticky note which is passed into this method.
	 * @param stickyNoteId
	 * @return
	 */
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
	
	/**
	 * Retrieve all sticky notes by a given id
	 * @param uniqueKey
	 * @return
	 */
	public List<StickyNote> retrieveStickyNotesByUniqueKey(String uniqueKey) {
		log.info("Entered retrieveStickyNotesByUniqueKey() StickyNoteDao.");
		List<StickyNote> stickyNotes = null;
			try {
				stickyNotes = this.jdbcTemplate.query(selectStickyNotesByUniqueKey, new StickyNoteMapper(), uniqueKey);
				log.info("Successfully retrieved StickyNotes from sticky_notes table. " + stickyNotes.size() + " for uniqueKey: " + uniqueKey);
	    	
			} catch(Exception e) {
				log.info(e.getLocalizedMessage());
			}
			return stickyNotes;
	}

}
