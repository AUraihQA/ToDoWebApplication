package com.persistence.dtos;

public class ToDoDTO {
	private Long id;
	private String description;
	private String dateCreated;
	private String deadlineDate;
	private String completion;
	
	public ToDoDTO() {
		super();
	}

	public ToDoDTO(Long id, String description, String dateCreated, String deadlineDate, String completion) {
		super();
		this.id = id;
		this.description = description;
		this.dateCreated = dateCreated;
		this.deadlineDate = deadlineDate;
		this.completion = completion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public String getCompletion() {
		return completion;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
	}

	
	

}
