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
public class ReminderPollingDao {

	private static Log log = LogFactory.getLog(ReminderPollingDao.class);

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	//java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	private static final String insertIntoSticky = "INSERT INTO stickynotes_db.sticky_notes " + 
			                                             "(id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED) " + 
			                                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String selectStickyNotes = "SELECT id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED FROM stickynotes_db.sticky_notes";
	
	
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
	
	/*
	@Transactional
	public int insertStickyNote(int id, String uniqueKey, String title, String description, java.sql.Timestamp reminderDate, String phone, String email, java.sql.Timestamp dateCreated) {
		log.info("Entered insertStickyNote() ReminderPollingDao.");
		int result = 0;
		try {
			int stripeDetailsRecCount = this.jdbcTemplate.queryForObject(getStripeDetailsRecCountQry, Integer.class);
			if(stripeDetailsRecCount > 0) {
				int maxStripeDetailsId = this.jdbcTemplate.queryForObject(insertIntoSticky, Integer.class);
				result = this.jdbcTemplate.update(insertStripePaymentDetailsQry, maxStripeDetailsId +1, userId, stripeCustomerId, stripeProductId,
						stripeSubscriptionId, stripePlanId, stripePriceId, stripeCouponUsed, stripeEmail, date, date);
			}else {
				result = this.jdbcTemplate.update(insertStripePaymentDetailsQry, 1, userId, stripeCustomerId, stripeProductId,
						stripeSubscriptionId, stripePlanId, stripePriceId, stripeCouponUsed, stripeEmail, date, date);
			}
			log.info("Successfully saved Stripe Subscription Plan details.");
				    
	    }catch(Exception e) {
    	    log.info(e.getLocalizedMessage());
    	    throw new ChContactException("Due to increased traffic, we were unable save Subscription plan details.  This is temporary.  Please try again later.");
        }   	    
	    return result;
	}
	*/
}
