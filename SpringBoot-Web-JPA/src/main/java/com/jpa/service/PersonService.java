package com.jpa.service;

import com.jpa.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dragon-zg
 * @date 2019/4/24 10:39
 **/
@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

}
