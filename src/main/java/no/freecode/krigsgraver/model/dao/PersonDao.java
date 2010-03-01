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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import no.freecode.krigsgraver.model.Camp;
import no.freecode.krigsgraver.model.CauseOfDeath;
import no.freecode.krigsgraver.model.Cemetery;
import no.freecode.krigsgraver.model.FlexibleDate;
import no.freecode.krigsgraver.model.Grave;
import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.PersonDetails;
import no.freecode.krigsgraver.model.Rank;
import no.freecode.krigsgraver.model.Stalag;
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
        
        ArrayList<Grave> graves = new ArrayList<Grave>();
        for (Grave grave : command.getLazyGraves()) {
            if (!grave.isDelete()) {
                graves.add(grave);
            }
        }
        person.setGraves(graves);

        ArrayList<CauseOfDeath> causesOfDeath = new ArrayList<CauseOfDeath>();
        for (CauseOfDeath cause : command.getLazyCausesOfDeath()) {
            if (cause != null) {
                causesOfDeath.add(cause);
            }
        }
        person.setCausesOfDeath(causesOfDeath);

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
    public List<Person> search(String queryString, Paginator paginator) throws ParseException {

        String[] fields = new String[] { 
                "westernDetails.firstName", "westernDetails.lastName",
                "cyrillicDetails.firstName", "cyrillicDetails.lastName" };

        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new SimpleAnalyzer());
//        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
        org.apache.lucene.search.Query query = parser.parse(queryString);

        FullTextQuery fullTextQuery = Search.getFullTextSession(sessionFactory.getCurrentSession()).createFullTextQuery(query, Person.class);

        fullTextQuery.setFirstResult(Paginator.ITEMS_PER_PAGE * (paginator.getPageNumber() - 1));
        fullTextQuery.setMaxResults(Paginator.ITEMS_PER_PAGE + 1);

        @SuppressWarnings("unchecked")
        List<Person> results = fullTextQuery.list();

        logger.debug("Search returned " + fullTextQuery.getResultSize() + " results. List size=" + results.size());
        paginator.setNumberOfResults(fullTextQuery.getResultSize());

        return paginator.paginate(results);
//        return results;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        
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

            i++; // nationality
//            person.setNationality(v[i++]);

            String stalagString = v[i++];
            if (StringUtils.isNotBlank(stalagString)) {
                Stalag stalag = genericDao.getUnique(Stalag.class, "name", stalagString);
                if (stalag == null) {
                    stalag = new Stalag();
                    stalag.setName(stalagString);
                    genericDao.save(stalag);
                }
                person.setStalag(stalag);
            }

            person.setPrisonerNumber(createInteger(v[i++]));
            
            String rankString = v[i++];
            if (StringUtils.isNotBlank(rankString)) {
                Rank rank = genericDao.getUnique(Rank.class, "name", rankString);
                if (rank == null) {
                    rank = new Rank(rankString);
                    genericDao.save(rank);
                }
                person.setRank(rank);
            }
            
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

            String campString = v[i++];
            if (StringUtils.isNotBlank(campString)) {
                Camp camp = genericDao.getUnique(Camp.class, "name", campString);
                if (camp == null) {
                    camp = new Camp();
                    camp.setName(campString);
                    genericDao.save(camp);
                }
                person.setCamp(camp);
            }
            
            String cemeteryString = v[i++];
            
            i++; // row - not much there
            
            String graveNumber = v[i++];
            String graveDateString = v[i++];
            Date graveDate = null;
            
            try {
                if (StringUtils.isNotBlank(graveDateString)) {
                    graveDate = dateFormat.parse(graveDateString);
                }
                
            } catch (java.text.ParseException e) {
                // ignore parse errors for now - this is just to get some data
            }
            
            Grave grave = new Grave();
            grave.setGraveNumber(createInteger(graveNumber));
            if (graveDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(graveDate);
                grave.getDateOfBurial().setDay(cal.get(Calendar.DAY_OF_MONTH));
                grave.getDateOfBurial().setMonth(cal.get(Calendar.MONTH) + 1);
                grave.getDateOfBurial().setYear(cal.get(Calendar.YEAR));
            }

            if (StringUtils.isNotBlank(cemeteryString)) {
                Cemetery cemetery = genericDao.getUnique(Cemetery.class, "name", cemeteryString);
                if (cemetery == null) {
                    cemetery = new Cemetery();
                    cemetery.setName(cemeteryString);
                    genericDao.save(cemetery);
                }

                grave.setCemetery(cemetery);
            }
            
            genericDao.save(grave);
            person.getGraves().add(grave);
            
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
    
    public static void main(String[] args) {
        try {
            System.out.println(new SimpleDateFormat("MM/dd/yyyy").parse("11/3/1943"));
            
        } catch (java.text.ParseException e) {
            // ignore...
        }
    }
}
