package com.persistence.dtos;

import java.util.List;

import com.persistence.domain.ToDoDomain;

public class ListDTO {
	private Long id;
	private String name;
	private List<ToDoDomain> toDoList;
	
	public ListDTO() {
		super();
	}

	public ListDTO(Long id, String name, List<ToDoDomain> toDoList) {
		super();
		this.id = id;
		this.name = name;
		this.toDoList = toDoList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ToDoDomain> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<ToDoDomain> toDoList) {
		this.toDoList = toDoList;
	}
	
	
	
	

}
