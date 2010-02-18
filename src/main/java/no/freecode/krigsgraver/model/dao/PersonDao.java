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
import java.util.ArrayList;
import java.util.List;

import no.freecode.krigsgraver.model.CauseOfDeath;
import no.freecode.krigsgraver.model.FlexibleDate;
import no.freecode.krigsgraver.model.Grave;
import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.PersonDetails;
import no.freecode.krigsgraver.web.PersonCommandObject;

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
import org.hibernate.search.FullTextSession;
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
    
    @Autowired
    private GenericDao genericDao;

    /**
     * Update or create the person.
     */
    public void savePerson(Person person) {
        sessionFactory.getCurrentSession().merge(person);
    }

    /**
     * Update or create the person.
     */
    public void savePersonCommandObject(PersonCommandObject command) {
        Person person = command.getPerson();
        person.setGraves(new ArrayList<Grave>(command.getLazyGraves()));
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
                "westernDetails.firstName", "westernDetails.lastName",
                "cyrillicDetails.firstName", "cyrillicDetails.lastName" };

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
     * Index all Person objects in the search engine.
     */
    public void indexData() {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        
        @SuppressWarnings("unchecked")
        List<Person> personList = sessionFactory.getCurrentSession().createCriteria(Person.class).list();
        for (Person person : personList) {
            fullTextSession.index(person);
        }
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

            PersonDetails cyrillicDetails = new PersonDetails();
            cyrillicDetails.setLastName(v[i++]);
            cyrillicDetails.setFirstName(v[i++]);
            cyrillicDetails.setNameOfFather(v[i++]);
            cyrillicDetails.setPlaceOfBirth(v[i++]);
            person.setCyrillicDetails(cyrillicDetails);
            
            PersonDetails westernDetails = new PersonDetails();
            westernDetails.setLastName(v[i++]);
            westernDetails.setFirstName(v[i++]);
            westernDetails.setNameOfFather(v[i++]);

            person.setDateOfBirth(new FlexibleDate(createInteger(v[i++]),
                    createInteger(v[i++]), createInteger(v[i++])));
            
            westernDetails.setPlaceOfBirth(v[i++]);
            person.setWesternDetails(westernDetails);
            
            person.setNationality(v[i++]);

            i++; // stalag
            
            person.setPrisonerNumber(createInteger(v[i++]));
            person.setRank(v[i++]);
            
            i++;  // pårørende

            person.setDateOfDeath(new FlexibleDate(createInteger(v[i++]),
                    createInteger(v[i++]), createInteger(v[i++])));

            person.setPlaceOfDeath(v[i++]);

//            String cause = v[i++];
            person.setCausesOfDeath(getCauses(v[i++]));
//            if (StringUtils.isNotEmpty(cause)) {
//                CauseOfDeath causeOfDeath = new CauseOfDeath();
//                causeOfDeath.setCause(cause);
//                person.getCausesOfDeath().add(causeOfDeath);
//            }

            i++; // norsk leir
            
            savePerson(person);
        }
    }

    /**
     * Temporary hack to get some different causes when parsing the CSV.
     * 
     * @param str
     * @return
     */
    private List<CauseOfDeath> getCauses(String str) {
        
        if (StringUtils.isNotEmpty(str)) {
            List<CauseOfDeath> causes = new ArrayList<CauseOfDeath>();

            for (String s1 : StringUtils.splitByWholeSeparator(str, ", ")) {
                for (String s2 : StringUtils.splitByWholeSeparator(s1, " und ")) {
                    for (String s3 : StringUtils.splitByWholeSeparator(s2, " u. ")) {
                        
                        CauseOfDeath cause = genericDao.getUnique(CauseOfDeath.class, "cause", s3);
                        if (cause == null) {
                            cause = new CauseOfDeath();
                            cause.setCause(s3);
                        }
                            
                        causes.add(cause);
                    }
                }
            }
            
            return causes;
            
        } else {
            return null;
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
