package com.rest;

import java.util.ArrayList;

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
import com.persistence.domain.ListDomain;
import com.persistence.dtos.ListDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ListControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private ListDTO mapToDTO(ListDomain model) {
		return this.mapper.map(model, ListDTO.class);
	}
	
	private final int ID = 1;
	
	
	@Test
	public void create() throws Exception {
		ListDomain contentBody = new ListDomain(3L, "NEW", new ArrayList<>());
		ListDTO expectedResult = mapToDTO(contentBody); 
		expectedResult.setId(3L);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/list/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test 
	public void readOne() throws Exception {
		ListDTO expectedResult = new ListDTO(1L, "Work", new ArrayList<>());
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "http://localhost:8080/list/read/" + ID);
		
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void update() throws Exception {
		ListDomain contentBody = new ListDomain(1L, "NEW", new ArrayList<>());
		ListDTO expectedResult = mapToDTO(contentBody); 
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/list/update/" + ID)
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
				.request(HttpMethod.DELETE,"http://localhost:8080/list/delete/2");
		
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
	
	
	@Test
	public void deleteListFailure() throws Exception {
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE,"http://localhost:8080/list/delete/10");
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isInternalServerError();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
	
	

}
