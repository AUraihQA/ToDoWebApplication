package com.rest;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persistence.domain.ToDoDomain;
import com.persistence.dtos.ToDoDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ToDoControlletIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper jsonifier;

	private ToDoDTO mapToDTO(ToDoDomain model) {
		return this.mapper.map(model, ToDoDTO.class);
	}

	private final int ID = 1;

	@Test
	public void create() throws Exception {
		ToDoDomain contentBody = new ToDoDomain(4L, "Test", "11th Feb 2021", "21st Feb 2021", "No", null);
		ToDoDTO expectedResult = mapToDTO(contentBody); 
		expectedResult.setId(4L);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/ToDo/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test 
	public void readOne() throws Exception {
		ToDoDTO expectedResult = new ToDoDTO(1L, "Finish Task 1", "11th February 2021", "21st February 2021", "No");
	
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
		.request(HttpMethod.GET, "http://localhost:8080/ToDo/read/" + ID);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		
	}
	
	@Test
	public void update() throws Exception {
		ToDoDomain contentBody = new ToDoDomain(1L, "Finish Task 1", "11th February 2021", "21st February 2021", "Yes", null);
		ToDoDTO expectedResult = mapToDTO(contentBody);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/ToDo/update/" + ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void delete() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE,"http://localhost:8080/ToDo/delete/2");
		
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
	
	
	@Test
	public void deleteListFailure() throws Exception {
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE,"http://localhost:8080/ToDo/delete/10");
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isInternalServerError();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
	
	
}
