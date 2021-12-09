package com.edwardjones.reminder.domain;

public class StickyNote {

	private int id;
	private String uniqueKey;
	private String title;
	private String description;
	private String reminderDate;
	private String phone;
	private String email;  
	private String dateCreated;
	
	public StickyNote() {}
	
	public StickyNote(int id, String uniqueKey,String title, String description, String reminderDate, String phone, String email,
			String dateCreated) {
		super();
		this.id = id;
		this.uniqueKey = uniqueKey;
		this.title = title;
		this.description = description;
		this.reminderDate = reminderDate;
		this.phone = phone;
		this.email = email;
		this.dateCreated = dateCreated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReminderDate() {
		return reminderDate;
	}
	public void setReminderDate(String reminderDate) {
		this.reminderDate = reminderDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
