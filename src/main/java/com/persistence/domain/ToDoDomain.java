package com.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ToDoDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String Description;
	private String DateCreated;
	private String DeadlineDate;
	private String Completion;
	
	@ManyToOne
	private Long ListID;

	public ToDoDomain() {
		super();
	}

	public ToDoDomain(Long id, String description, String dateCreated, String deadlineDate, String completion,
			Long listID) {
		super();
		this.id = id;
		Description = description;
		DateCreated = dateCreated;
		DeadlineDate = deadlineDate;
		Completion = completion;
		ListID = listID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}

	public String getDeadlineDate() {
		return DeadlineDate;
	}

	public void setDeadlineDate(String deadlineDate) {
		DeadlineDate = deadlineDate;
	}

	public String getCompletion() {
		return Completion;
	}

	public void setCompletion(String completion) {
		Completion = completion;
	}

	public Long getListID() {
		return ListID;
	}

	public void setListID(Long listID) {
		ListID = listID;
	}
	
	

}
