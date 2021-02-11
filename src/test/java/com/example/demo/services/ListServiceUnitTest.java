package com.example.demo.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.persistence.domain.ListDomain;
import com.persistence.dtos.ListDTO;
import com.persistence.repos.ListRepo;
import com.services.ListService;

@SpringBootTest
public class ListServiceUnitTest {
	
	@MockBean
	private ModelMapper mockedMapper;
	
	@MockBean 
	ListRepo mockedRepo;
	
	@Autowired
	private ListService service;
	
	@Test
	public void create() {
		ListDomain TEST_LIST = new ListDomain(1L, "Test", null);
		ListDTO TEST_DTO = new ListDTO(1L, "Test", null);
		
		Mockito.when(this.mockedRepo.save(Mockito.any(ListDomain.class))).thenReturn(TEST_LIST);
		Mockito.when(this.mockedMapper.map(TEST_LIST, ListDTO.class)).thenReturn(TEST_DTO);
		
		ListDTO result = this.service.create(TEST_LIST);
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(TEST_DTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(TEST_DTO);
		
	}

}
