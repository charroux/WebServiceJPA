package com.efrei.JPAExample;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
	
	List<Person> findByName(String name);

}
