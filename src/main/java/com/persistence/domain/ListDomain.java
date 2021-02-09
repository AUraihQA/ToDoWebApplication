package com.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class ListDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String Name;
	
	@OneToMany(mappedBy = "ListID", fetch= FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ToDoDomain> catList;

	public ListDomain() {
		super();
	}

	public ListDomain(Long id, String name, List<ToDoDomain> catList) {
		super();
		this.id = id;
		Name = name;
		this.catList = catList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<ToDoDomain> getCatList() {
		return catList;
	}

	public void setCatList(List<ToDoDomain> catList) {
		this.catList = catList;
	}
	
	

}
