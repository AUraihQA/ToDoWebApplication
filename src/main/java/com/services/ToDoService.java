package com.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.persistence.domain.ToDoDomain;
import com.persistence.dtos.ToDoDTO;
import com.persistence.repos.ToDoRepo;
import com.utils.MyBeanUtils;

@Service
public class ToDoService {
	private ToDoRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public ToDoService(ToDoRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private ToDoDTO mapToDTO(ToDoDomain model) {
		return this.mapper.map(model, ToDoDTO.class);
	}
	
	public ToDoDTO create(ToDoDomain list) {
		return this.mapToDTO(this.repo.save(list));
	}
	
	public List<ToDoDTO> readAll() {
		List<ToDoDomain> dbList = this.repo.findAll();
		List<ToDoDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return resultList;
	}
	
	public ToDoDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public ToDoDTO update(Long id, ToDoDomain newDetails) {
		ToDoDomain updatedList = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(newDetails, updatedList);
		
		return this.mapToDTO(this.repo.save(updatedList));
	}
	
	public boolean delete(Long id) {
		try {
			this.repo.deleteById(id);

			boolean flag = !this.repo.existsById(id);

			return flag;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	

}
