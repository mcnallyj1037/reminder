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
import com.edwardjones.reminder.exception.ReminderException;

@Component
public class ReminderPollingDao {

	private static Log log = LogFactory.getLog(ReminderPollingDao.class);

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	private static final String selectStickyNotes = "SELECT id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED "
			                                             + "FROM stickynotes_db.sticky_notes "
			                                             + "WHERE REMINDER_DATE IS NOT NULL "
			                                             + "OR REMINDER_DATE != '0000-00-00 00:00:00' "
			                                             + "AND REMINDER_DATE <= ?";
	
	private static final String isStickyNoteExist = "SELECT COUNT(*) "
			                                             + "FROM stickynotes_db.sticky_notes "
			                                             + "WHERE UNIQUE_KEY = ?";
	
	private static final String updateReminderDateToEmpty = "UPDATE stickynotes_db.sticky_notes "
			                                                     + "SET REMINDER_DATE = '0000-00-00 00:00:00' "
			                                                     + "WHERE UNIQUE_KEY = ?";
	
	/**
	 * Retrieve all sticky notes and return a list of StickyNote objects where reminder date is set and is less than current date/time.
	 * @return
	 * @throws ReminderException 
	 */
	@Transactional
	public List<StickyNote> retrieveAllStickyNotes(java.sql.Timestamp date) throws ReminderException {
		log.info("Entered retrieveAllStickyNotes() in ReminderPollingDao.");
	    List<StickyNote> stickyNoteList = null;
	    try {
	    	stickyNoteList = this.jdbcTemplate.query(selectStickyNotes, new StickyNoteMapper(), new Object[]{date});
	    	log.info("Successfully retrieved user from STICKY_NOTES table :" + stickyNoteList.size());
	    		
	    }catch(Exception e) {
	    	log.info(e.getLocalizedMessage());
	    	throw new ReminderException(e.getLocalizedMessage());
	    }
	    return stickyNoteList;
	}
	
	/**
	 * Make the date/time field for "Reminder" in the STICKY_NOTES table empty.
	 * @param uniqueKey
	 * @return
	 * @throws ReminderException 
	 */
	@Transactional
	public int updateReminderToBeEmpty(String uniqueKey) throws ReminderException {
		log.info("Entered updateReminderToBeEmpty() in ReminderPollingDao.");
		int result = 0;
    	try {
    		int isStickyNoteExizst = this.jdbcTemplate.queryForObject(isStickyNoteExist, Integer.class, uniqueKey);
    		if(isStickyNoteExizst > 0) {
    			result = this.jdbcTemplate.update(updateReminderDateToEmpty, new Object[]{uniqueKey});
    		}
    		
    		log.info("Reminder updated to be empty: 1 = yes / 0 = no: " + result);
    		
    	}catch(Exception e) {
    		log.info(e.getLocalizedMessage());
    		throw new ReminderException(e.getLocalizedMessage());
    	}
    	return result;
	}
	

}
