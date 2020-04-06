package com.example.monitoring.repository;

import com.example.monitoring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

  List<Person> findAll();
}
