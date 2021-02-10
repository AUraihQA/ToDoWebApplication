package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistence.domain.ToDoDomain;
import com.persistence.dtos.ToDoDTO;
import com.services.ToDoService;


@RestController
@RequestMapping("/ToDo")
@CrossOrigin
public class ToDoController {
	private ToDoService service;

	@Autowired
	public ToDoController(ToDoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ToDoDTO> create(@RequestBody ToDoDomain toDo) {
		return new ResponseEntity<ToDoDTO> (this.service.create(toDo), HttpStatus.CREATED);
	}
	
	@GetMapping("/getToDo")
	public ResponseEntity<List<ToDoDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoDTO> readOne(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.service.readOne(id));
	}
	
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity <ToDoDTO> update(@PathVariable("id") Long id, @RequestBody ToDoDomain toDo) {
		return new ResponseEntity<ToDoDTO>(this.service.update(id, toDo), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable ("id") Long id) {
		return this.service.delete(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT):
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
