package com.edwardjones.reminder.domain;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StickyNote {

	
	private int id;
	
	@JsonProperty(value="uniqueKey", required = true)
    @NotNull(message="Please provide a valid uniqueKey")
	private String uniqueKey;
	
	@JsonProperty(value="title", required = false)
    @NotNull(message="Please provide a valid title")
	private String title;
	
	@JsonProperty(value="description", required = false)
    @NotNull(message="Please provide a valid description")
	private String description;
	
	@JsonProperty(value="reminderDate", required = false)
    @NotNull(message="Please provide a valid reminderDate")
	private String reminderDate;
	
	@JsonProperty(value="phone", required = false)
    @NotNull(message="Please provide a valid phone")
	private String phone;
	
	@JsonProperty(value="email", required = false)
    @NotNull(message="Please provide a valid email")
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
