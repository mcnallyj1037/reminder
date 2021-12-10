package com.edwardjones.reminder.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StickyNoteMapper implements RowMapper<StickyNote> {

    @Override
    public StickyNote mapRow(ResultSet rs, int rowNum) throws SQLException {
    	StickyNote stickyNote = new StickyNote();
    	stickyNote.setId(rs.getInt("id"));
    	stickyNote.setUniqueKey(rs.getString("UNIQUE_KEY"));
        stickyNote.setTitle(rs.getString("TITLE"));
    	stickyNote.setDescription(rs.getString("DESCRIPTION"));
    	stickyNote.setReminderDate(rs.getString("REMINDER_DATE"));
    	stickyNote.setPhone(rs.getString("PHONE"));
    	stickyNote.setEmail(rs.getString("EMAIL"));
    	stickyNote.setDateCreated(rs.getString("DATE_CREATED"));
    	return stickyNote;
    }


}
