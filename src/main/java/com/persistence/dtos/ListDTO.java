package com.persistence.dtos;

import java.util.List;

public class ListDTO {
	private Long id;
	private String name;
	private List<ToDoDTO> toDoList;

	public ListDTO() {
		super();
	}

	public ListDTO(Long id, String name, List<ToDoDTO> toDoList) {
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

	public List<ToDoDTO> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<ToDoDTO> toDoList) {
		this.toDoList = toDoList;
	}

}
