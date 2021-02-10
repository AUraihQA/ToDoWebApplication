package com.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persistence.domain.ListDomain;

@Repository
public interface ListRepo extends JpaRepository <ListDomain, Long> {

}
