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

	private String description;
	private String dateCreated;
	private String deadlineDate;
	private String completion;

	@ManyToOne
	private ListDomain myList;

	public ToDoDomain() {
		super();
	}

	public ToDoDomain(Long id, String description, String dateCreated, String deadlineDate, String completion,
			ListDomain myList) {
		super();
		this.id = id;
		this.description = description;
		this.dateCreated = dateCreated;
		this.deadlineDate = deadlineDate;
		this.completion = completion;
		this.myList = myList;
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

	public ListDomain getMyList() {
		return myList;
	}

	public void setMyList(ListDomain myList) {
		this.myList = myList;
	}

}
