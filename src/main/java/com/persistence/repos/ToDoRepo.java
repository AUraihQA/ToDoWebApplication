package com.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persistence.domain.ToDoDomain;

@Repository
public interface ToDoRepo extends JpaRepository <ToDoDomain, Long> {

}
