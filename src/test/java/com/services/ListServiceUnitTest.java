package com.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Test
	public void readAll() {
		ListDomain TEST_LIST = new ListDomain(1L, "Test", null);
		List<ListDomain> testListDomain = new ArrayList<>();
		testListDomain.add(TEST_LIST);
		ListDTO TEST_DTO = new ListDTO(1L, "Test", null);
		List<ListDTO> testListDTO = new ArrayList<>();
		testListDTO.add(TEST_DTO);

		Mockito.when(this.mockedRepo.findAll()).thenReturn(testListDomain);
		Mockito.when(this.mockedMapper.map(TEST_LIST, ListDTO.class)).thenReturn(TEST_DTO);

		List<ListDTO> result = this.service.readAll();

		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testListDTO);

	}

	@Test
	public void readOne() {
		ListDomain TEST_LIST = new ListDomain(1L, "Test", null);
		ListDTO TEST_DTO = this.mockedMapper.map(TEST_LIST, ListDTO.class);

		Mockito.when(this.mockedRepo.findById(TEST_LIST.getId())).thenReturn(Optional.of(TEST_LIST));

		ListDTO result = this.service.readOne(1L);

		Assertions.assertThat(result).isEqualTo(TEST_DTO);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);

	}
	
	@Test
	public void update() {
		ListDomain TEST_LIST = new ListDomain(1L, "UpdatedTest", null);
		ListDTO TEST_DTO = new ListDTO(1L, "UpdatedTest", null);
		
		Mockito.when(this.mockedRepo.findById(TEST_LIST.getId())).thenReturn(Optional.of(TEST_LIST));
		Mockito.when(this.mockedRepo.save(Mockito.any(ListDomain.class))).thenReturn(TEST_LIST);
		Mockito.when(this.mockedMapper.map(TEST_LIST, ListDTO.class)).thenReturn(TEST_DTO);
		
		ListDTO result = this.service.update(1L, TEST_LIST);
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(TEST_DTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(TEST_DTO);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(ListDomain.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(TEST_LIST, ListDTO.class);
	}
	
	@Test 
	public void delete() {
		ListDomain TEST_LIST = new ListDomain(1L, "Test", null);
		boolean expectedResult = true;
		
		Mockito.when(this.mockedRepo.
				findById(TEST_LIST.getId())).thenReturn(Optional.of(TEST_LIST));

		boolean result = this.service.delete(1L);

		Assertions.assertThat(result).isEqualTo(expectedResult);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).deleteById(1L);

	}

}
