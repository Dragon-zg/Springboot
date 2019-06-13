package com.jpa.repository;

import com.jpa.model.entity.onetoone.Person;
import com.jpa.repository.base.BaseRepository;

/**
 * PersonRepository
 *
 * @author Dragon-zg
 * @date 2019/4/24 16:17
 */
public interface PersonRepository extends BaseRepository<Person, Long> {
}
