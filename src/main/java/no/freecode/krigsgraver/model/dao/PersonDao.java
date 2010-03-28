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
import java.util.TreeSet;

import no.freecode.krigsgraver.model.Camp;
import no.freecode.krigsgraver.model.CauseOfDeath;
import no.freecode.krigsgraver.model.Cemetery;
import no.freecode.krigsgraver.model.FlexibleDate;
import no.freecode.krigsgraver.model.Grave;
import no.freecode.krigsgraver.model.GraveComparator;
import no.freecode.krigsgraver.model.Nationality;
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
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
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

        TreeSet<Grave> graves = new TreeSet<Grave>(new GraveComparator());
//        ArrayList<Grave> graves = new ArrayList<Grave>();
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

    public void delete(long id) {
        Person person = getPerson(id);
        sessionFactory.getCurrentSession().delete(person);
    }

    /**
     * Fetch a person from the DB.
     */
    @Transactional(readOnly = true)
    public Person getPerson(long id) {
        return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
    }

    /**
     * Fetch a person, including graves etc. from the DB.
     */
    @Transactional(readOnly = true)
    public Person getCompletelyLoadedPerson(long id) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Person.class);
        crit.add(Restrictions.idEq(id));
        crit.setFetchMode("graves", FetchMode.JOIN);
        crit.setFetchMode("causesOfDeath", FetchMode.JOIN);
        return (Person) crit.uniqueResult();
    }

    /**
     * Fetch a list of all Person objects from the DB.
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Person.class).list();
    }

    /**
     * Search for people based on a Lucene query string.
     * 
     * @param queryString
     * @param paginator
     * @return
     * @throws ParseException
     */
    @Transactional(readOnly = true)
    public List<Person> search(String queryString, Paginator paginator, boolean anonymous) throws ParseException {

        Session currentSession = sessionFactory.getCurrentSession();

        String[] fields;
        if (anonymous) {
           fields = new String[] { 
                "firstName", "nameOfFather", "lastName", "fullName",
                "placeOfBirth",
                "dateOfBirth.year", "dateOfBirth.month", "dateOfBirth.day",
                "nationality.name", "prisonerNumber",
                "rank.name", "camp.name", "stalag.name",
                "placeOfDeath",
                "dateOfDeath.year", "dateOfDeath.month", "dateOfDeath.day",
                "graves.cemetery.name"
                };
        } else {
            fields = new String[] { 
                    "firstName", "nameOfFather", "lastName", "fullName",
                    "placeOfBirth",
                    "dateOfBirth.year", "dateOfBirth.month", "dateOfBirth.day",
                    "nationality.name", "prisonerNumber",
                    "rank.name", "camp.name", "stalag.name",
                    "placeOfDeath",
                    "dateOfDeath.year", "dateOfDeath.month", "dateOfDeath.day",
                    "causeOfDeathDescription", "causesOfDeath.name", "causesOfDeath.causeGroup",
                    "remarks",
                    "graves.cemetery.name"
                    };
        }

        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
//        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new SimpleAnalyzer());
        parser.setDefaultOperator(Operator.AND);

        org.apache.lucene.search.Query query = parser.parse(queryString);

        FullTextQuery fullTextQuery = Search.getFullTextSession(currentSession).createFullTextQuery(query, Person.class);

        fullTextQuery.setFirstResult(paginator.getItemsPerPage() * (paginator.getPageNumber() - 1));
        fullTextQuery.setMaxResults(paginator.getItemsPerPage() + 1);
        
        @SuppressWarnings("unchecked")
        List<Person> results = fullTextQuery.list();

        logger.debug("Search returned " + fullTextQuery.getResultSize() + " results. List size=" + results.size());
        paginator.setNumberOfResults(fullTextQuery.getResultSize());

        return paginator.paginate(results);
    }
    
    /**
     * Index all Person objects in the search engine.
     */
    public void indexData() {
        logger.info("Updating search index.");
        
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
            
            person.setObdNumber(createLong(v[i++]));

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


            String nationalityString = v[i++];
            if (StringUtils.isNotBlank(nationalityString)) {
                Nationality nationality = genericDao.getUnique(Nationality.class, "name", nationalityString);
                if (nationality == null) {
                    nationality = new Nationality();
                    nationality.setName(nationalityString);
                    genericDao.save(nationality);
                }
                person.setNationality(nationality);
            }

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
            grave.setGraveNumber(graveNumber);
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
                        
                        CauseOfDeath cause = genericDao.getUnique(CauseOfDeath.class, "name", s3);
                        if (cause == null) {
                            cause = new CauseOfDeath();
                            cause.setName(s3);
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

    private static Long createLong(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        } else {
            try {
                return NumberUtils.createLong(new String(str).replaceFirst("^0+", ""));
            } catch (NumberFormatException e) {
                // ignore it for now. XXX
                return null;
            }
        }
    }
}
