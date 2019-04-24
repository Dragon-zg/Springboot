package com.jpa.dao;

import com.jpa.entity.onetoone.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Integer> {
}
