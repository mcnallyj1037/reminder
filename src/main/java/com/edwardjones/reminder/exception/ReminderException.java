package com.edwardjones.reminder.exception;

@SuppressWarnings("serial")
public class ReminderException extends Exception {

	@SuppressWarnings("unused")
	private String errorMessage;
	
	public ReminderException(String errorMessage) {
        super(errorMessage);
    }	

}
