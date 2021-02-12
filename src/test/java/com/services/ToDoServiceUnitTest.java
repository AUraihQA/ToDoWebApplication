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

import com.persistence.domain.ToDoDomain;
import com.persistence.dtos.ToDoDTO;
import com.persistence.repos.ToDoRepo;

@SpringBootTest
public class ToDoServiceUnitTest {

	@MockBean
	private ModelMapper mockedMapper;

	@MockBean
	ToDoRepo mockedRepo;

	@Autowired
	private ToDoService service;

	@Test
	public void create() {
		ToDoDomain TEST_TODO = new ToDoDomain(1L, "Test", "11th Feb 2021", "21st Feb 2021", "No", null);
		ToDoDTO TEST_DTO = new ToDoDTO(1L, "Test", "11th Feb 2021", "21st Feb 2021", "No");

		Mockito.when(this.mockedRepo.save(Mockito.any(ToDoDomain.class))).thenReturn(TEST_TODO);
		Mockito.when(this.mockedMapper.map(TEST_TODO, ToDoDTO.class)).thenReturn(TEST_DTO);

		ToDoDTO result = this.service.create(TEST_TODO);

		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(TEST_DTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(TEST_DTO);

	}

	@Test
	public void readAll() {
		ToDoDomain TEST_TODO = new ToDoDomain(1L, "Test", "11th Feb 2021", "21st Feb 2021", "No", null);
		List<ToDoDomain> testToDoDomain = new ArrayList<>();
		testToDoDomain.add(TEST_TODO);
		ToDoDTO TEST_DTO = new ToDoDTO(1L, "Test", "11th Feb 2021", "21st Feb 2021", "No");
		List<ToDoDTO> testToDoDTO = new ArrayList<>();
		testToDoDTO.add(TEST_DTO);

		Mockito.when(this.mockedRepo.findAll()).thenReturn(testToDoDomain);
		Mockito.when(this.mockedMapper.map(TEST_TODO, ToDoDTO.class)).thenReturn(TEST_DTO);

		List<ToDoDTO> result = this.service.readAll();

		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testToDoDTO);

	}

	@Test
	public void readOne() {
		ToDoDomain TEST_TODO = new ToDoDomain(1L, "Test", "11th Feb 2021", "21st Feb 2021", "No", null);
		ToDoDTO TEST_DTO = this.mockedMapper.map(TEST_TODO, ToDoDTO.class);

		Mockito.when(this.mockedRepo.findById(TEST_TODO.getId())).thenReturn(Optional.of(TEST_TODO));

		ToDoDTO result = this.service.readOne(1L);

		Assertions.assertThat(result).isEqualTo(TEST_DTO);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);

	}

	@Test
	public void update() {
		ToDoDomain TEST_TODO = new ToDoDomain(1L, "Test", "11th Feb 2021", "21st Feb 2021", "No", null);
		ToDoDTO TEST_DTO = new ToDoDTO(1L, "Test", "11th Feb 2021", "21st Feb 2021", "No");

		Mockito.when(this.mockedRepo.findById(TEST_TODO.getId())).thenReturn(Optional.of(TEST_TODO));
		Mockito.when(this.mockedRepo.save(Mockito.any(ToDoDomain.class))).thenReturn(TEST_TODO);
		Mockito.when(this.mockedMapper.map(TEST_TODO, ToDoDTO.class)).thenReturn(TEST_DTO);

		ToDoDTO result = this.service.update(1L, TEST_TODO);

		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(TEST_DTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(TEST_DTO);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(ToDoDomain.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(TEST_TODO, ToDoDTO.class);

	}

	@Test
	public void delete() {
		ToDoDomain TEST_TODO = new ToDoDomain(1L, "Test", "11th Feb 2021", "21st Feb 2021", "No", null);
		boolean expectedResult = true;

		Mockito.when(this.mockedRepo.findById(TEST_TODO.getId())).thenReturn(Optional.of(TEST_TODO));

		boolean result = this.service.delete(1L);

		Assertions.assertThat(result).isEqualTo(expectedResult);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).deleteById(1L);

	}

}
