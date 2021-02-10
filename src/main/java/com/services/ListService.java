package com.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistence.domain.ListDomain;
import com.persistence.dtos.ListDTO;
import com.persistence.repos.ListRepo;
import com.utils.MyBeanUtils;

@Service
public class ListService {
	private ListRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public ListService(ListRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private ListDTO mapToDTO(ListDomain model) {
		return this.mapper.map(model, ListDTO.class);
	}
	
	public ListDTO create(ListDomain list) {
		return this.mapToDTO(this.repo.save(list));
	}
	
	public List<ListDTO> readAll() {
		List<ListDomain> dbList = this.repo.findAll();
		List<ListDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return resultList;
	}
	
	public ListDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public ListDTO update(Long id, ListDomain newDetails) {
		ListDomain updatedList = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(newDetails, updatedList);
		
		return this.mapToDTO(this.repo.save(updatedList));
	}
	
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		
		return !this.repo.existsById(id);
	}

}
