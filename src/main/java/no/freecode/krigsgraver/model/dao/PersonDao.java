/**
 *  Project: Krigsgraver
 *  Created: Feb 8, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model.dao;

import java.util.List;

import no.freecode.krigsgraver.model.Person;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data access methods for Person entities.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Repository
@Transactional
public class PersonDao {
    
    private static final Logger logger = Logger.getLogger(PersonDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Update or create the person.
     */
    public void savePerson(Person person) {
        sessionFactory.getCurrentSession().merge(person);
    }

    /**
     * Fetch a person from the DB.
     */
    public Person getPerson(long id) {
        return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
    }

    /**
     * Fetch a list of all Person objects from the DB.
     */
    @SuppressWarnings("unchecked")
    public List<Person> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Person.class).list();
    }
    
}
