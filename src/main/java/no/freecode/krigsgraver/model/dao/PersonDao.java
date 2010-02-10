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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import no.freecode.krigsgraver.model.FlexibleDate;
import no.freecode.krigsgraver.model.Name;
import no.freecode.krigsgraver.model.Person;

import org.apache.commons.csv.CSVUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.Search;
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
    @Transactional(readOnly = true)
    public Person getPerson(long id) {
        return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
    }

    /**
     * Fetch a list of all Person objects from the DB.
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Person.class).list();
    }

    @Transactional(readOnly = true)
    public List<Person> findPersons(String queryString) throws ParseException {

        String[] fields = new String[] { 
                "westernName.first", "westernName.last",
                "cyrillicName.first", "cyrillicName.last" };

        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new SimpleAnalyzer());
        org.apache.lucene.search.Query query = parser.parse(queryString);
        
        FullTextQuery fullTextQuery = Search.getFullTextSession(sessionFactory.getCurrentSession()).createFullTextQuery(query, Person.class);
//        fullTextQuery.setFirstResult(15); //start from the 15th element
//        fullTextQuery.setMaxResults(10); //return 10 elements

        @SuppressWarnings("unchecked")
        List<Person> results = fullTextQuery.list();
        
        logger.debug("Search returned " + fullTextQuery.getResultSize() + " results.");
        
        return results;
    }

    /**
     * Load data from a stream of CSV.
     * 
     * @param inputStream
     * @throws IOException 
     */
    public void loadCsvData(InputStream inputStream) throws IOException {
        
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = csvReader.readLine()) != null) {
            
            int i = 2;
            String[] v = CSVUtils.parseLine(line);
            
            logger.debug(ReflectionToStringBuilder.toString(v));
            
            Person person = new Person();
            
            person.setObdNumber(v[i++]);

            Name cyrillicName = new Name();
            cyrillicName.setLast(v[i++]);
            cyrillicName.setFirst(v[i++]);
            cyrillicName.setFather(v[i++]);
            person.setCyrillicName(cyrillicName);

            i++; // cyrillic place of birth
            
            Name westernName = new Name();
            westernName.setLast(v[i++]);
            westernName.setFirst(v[i++]);
            westernName.setFather(v[i++]);
            person.setWesternName(westernName);

            person.setDateOfBirth(new FlexibleDate(createInteger(v[i++]),
                    createInteger(v[i++]), createInteger(v[i++])));
            
            person.setPlaceOfBirth(v[i++]);
            person.setNationality(v[i++]);
            
            i++; // leir
            
            person.setPrisonerNumber(createInteger(v[i++]));
            person.setRank(v[i++]);
            
            i++;  // pårørende

            person.setDateOfDeath(new FlexibleDate(createInteger(v[i++]),
                    createInteger(v[i++]), createInteger(v[i++])));

            person.setPlaceOfDeath(v[i++]);
            person.setCauseOfDeath(v[i++]);
            
            i++; // one more leir
            
            savePerson(person);
        }
    }

    private static Integer createInteger(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        } else {
            try {
                return NumberUtils.createInteger(new String(str).replaceFirst("^0+", ""));
            } catch (NumberFormatException e) {
                // ignore it for now. XXX
                return null;
            }
        }
    }
}
