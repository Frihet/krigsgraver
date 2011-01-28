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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import no.freecode.krigsgraver.model.Camp;
import no.freecode.krigsgraver.model.Category;
import no.freecode.krigsgraver.model.CauseOfDeath;
import no.freecode.krigsgraver.model.CauseOfDeathComparator;
import no.freecode.krigsgraver.model.Cemetery;
import no.freecode.krigsgraver.model.FlexibleDate;
import no.freecode.krigsgraver.model.Grave;
import no.freecode.krigsgraver.model.Nationality;
import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.PersonDetails;
import no.freecode.krigsgraver.model.Rank;
import no.freecode.krigsgraver.model.Stalag;
import no.freecode.krigsgraver.web.PersonCommandObject;

import org.apache.commons.csv.CSVUtils;
import org.apache.commons.lang.StringUtils;
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

        List<Grave> graves = new ArrayList<Grave>();
//        TreeSet<Grave> graves = new TreeSet<Grave>(new GraveComparator());
        for (Grave grave : command.getLazyGraves()) {
            if (!grave.isDelete()) {
                graves.add(grave);
            }
        }
        person.setGraves(graves);
        
        TreeSet<CauseOfDeath> causesOfDeath = new TreeSet<CauseOfDeath>(new CauseOfDeathComparator());
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

        Category category = getCategory("Sovjetiske krigsgraver");
        
        String line;
        while ((line = csvReader.readLine()) != null) {
            logger.info("Parsing and inserting line: " + line);

            int i = 0;
//            String[] l = CSVUtils.parseLine(line);
            Line l = new Line(line);

            if ("memorial_id".equals(l.peek())) {
                logger.debug("Skipping, since this is just a title line (probably the first line).");
                continue;
            }
            
            Person person = new Person();
            person.setCategory(category);
            person.setObdNumber(createLong(l.next()));

            PersonDetails cyrillicDetails = new PersonDetails();
            cyrillicDetails.setLastName(l.next());
            cyrillicDetails.setFirstName(l.next());
            cyrillicDetails.setNameOfFather(l.next());
            
            PersonDetails westernDetails = new PersonDetails();
            westernDetails.setLastName(l.next());
            westernDetails.setFirstName(l.next());
            westernDetails.setNameOfFather(l.next());

            person.setDateOfBirth(new FlexibleDate(createInteger(l.next()),
                    createInteger(l.next()), createInteger(l.next())));
            
            cyrillicDetails.setPlaceOfBirth(l.next());
            westernDetails.setPlaceOfBirth(l.next());

            person.setCyrillicDetails(cyrillicDetails);
            person.setWesternDetails(westernDetails);


            String nationalityString = l.next();
            if (StringUtils.isNotBlank(nationalityString)) {
                Nationality nationality = genericDao.getUnique(Nationality.class, "name", nationalityString);
                if (nationality == null) {
                    nationality = new Nationality();
                    nationality.setName(nationalityString);
                    genericDao.save(nationality);
                }
                person.setNationality(nationality);
            }

            String stalagString = l.next();
            if (StringUtils.isNotBlank(stalagString)) {
                Stalag stalag = genericDao.getUnique(Stalag.class, "name", stalagString);
                if (stalag == null) {
                    stalag = new Stalag();
                    stalag.setName(stalagString);
                    genericDao.save(stalag);
                }
                person.setStalag(stalag);
            }

            person.setPrisonerNumber(createInteger(l.next()));
            
            String rankString = l.next();
            if (StringUtils.isNotBlank(rankString)) {
                Rank rank = genericDao.getUnique(Rank.class, "name", rankString);
                if (rank == null) {
                    rank = new Rank(rankString);
                    genericDao.save(rank);
                }
                person.setRank(rank);
            }

            person.setDateOfDeath(new FlexibleDate(createInteger(l.next()),
                    createInteger(l.next()), createInteger(l.next())));

            person.setPlaceOfDeath(l.next());
            person.setCausesOfDeath(getCauses(l.next()));

            String campString = l.next();
            if (StringUtils.isNotBlank(campString)) {
                Camp camp = genericDao.getUnique(Camp.class, "name", campString);
                if (camp == null) {
                    camp = new Camp();
                    camp.setName(campString);
                    genericDao.save(camp);
                }
                person.setCamp(camp);
            }

            String sourceString = l.next();  // kilde
            String remarksString = l.next(); // merknad
            String remarks;
            
            if (!StringUtils.isEmpty(sourceString)) {
                remarks = "Kilde: " + sourceString;
                if (!StringUtils.isEmpty(remarksString)) {
                    remarks += "\n" + remarksString;
                }
            } else {
                remarks = remarksString;
            }
            person.setRemarks(remarks);

            // Iterate until there are no more graves (the rest of the columns are grave definitions).
            while (l.hasNext()) {
                Grave grave = getGrave(
                        l.next(), // cemeteryString
                        new FlexibleDate(createInteger(l.next()), createInteger(l.next()), createInteger(l.next())), // dateOfBurial
                        l.next(), // graveRow
                        l.next(), // graveNumber
                        createBoolean(l.next()) // massGrave?
                );

                if (grave != null) {
                    person.getGraves().add(grave);

                } else {
                    // No more graves. Stop trying to make new graves.
                    break;
                }
            }

            // Save the graves, and make sure the "moved" attribute is set correctly (all but the last entry will have moved=true).
            Iterator<Grave> it = person.getGraves().iterator();
            while (it.hasNext()) {
                Grave grave = it.next();
                if (it.hasNext()) {
                    grave.setMoved(true);
                }
                genericDao.save(grave);
            }

            savePerson(person);
        }
    }

    /**
     * @return a new {@link Grave}, or null if all the fields are empty.
     */
    private Grave getGrave(String cemeteryString, FlexibleDate dateOfBurial, String graveRow, String graveNumber, boolean massGrave) {

        if (StringUtils.isNotBlank(cemeteryString) || !dateOfBurial.isEmpty() || StringUtils.isNotBlank(graveRow) || StringUtils.isNotBlank(graveNumber)) {
            Grave grave = new Grave();

            if (StringUtils.isNotBlank(cemeteryString)) {
                grave.setCemetery(getCemetery(cemeteryString));
            }
            grave.setDateOfBurial(dateOfBurial);
            grave.setGraveRow(graveRow);
            grave.setGraveNumber(graveNumber);
            grave.setMassGrave(massGrave);
            return grave;

        } else {
            // If all the fields are empty, we don't want to create a new grave.
            return null;
        }
    }
    
    /**
     * Return the category with a given name. Create and save it if it doesn't exist.
     */
    private Category getCategory(String name) {
        Category category = genericDao.getUnique(Category.class, "name", name);
        if (category == null) {
            category = new Category();
            category.setName(name);
            genericDao.save(category);
        }
        return category;
    }
    
    /**
     * Return the cemetery with a given name. Create and save it if it doesn't exist.
     */
    private Cemetery getCemetery(String name) {
        Cemetery cemetery = genericDao.getUnique(Cemetery.class, "name", name);
        if (cemetery == null) {
            cemetery = new Cemetery();
            cemetery.setName(name);
            genericDao.save(cemetery);
        }
        return cemetery;
    }
    
    
//    /**
//     * Load data from a stream of CSV.
//     * 
//     * @param inputStream
//     * @throws IOException
//     */
//    @Deprecated
//    public void loadCsvDataOld(InputStream inputStream) throws IOException {
//        
//        BufferedReader csvReader = new BufferedReader(new InputStreamReader(inputStream));
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        
//        String line;
//        while ((line = csvReader.readLine()) != null) {
//            
//            int i = 2;
//            String[] v = CSVUtils.parseLine(line);
//            
//            logger.debug(ReflectionToStringBuilder.toString(v));
//            
//            Person person = new Person();
//            
//            person.setObdNumber(createLong(v[i++]));
//
//            PersonDetails cyrillicDetails = new PersonDetails();
//            cyrillicDetails.setLastName(v[i++]);
//            cyrillicDetails.setFirstName(v[i++]);
//            cyrillicDetails.setNameOfFather(v[i++]);
//            cyrillicDetails.setPlaceOfBirth(v[i++]);
//            person.setCyrillicDetails(cyrillicDetails);
//            
//            PersonDetails westernDetails = new PersonDetails();
//            westernDetails.setLastName(v[i++]);
//            westernDetails.setFirstName(v[i++]);
//            westernDetails.setNameOfFather(v[i++]);
//
//            person.setDateOfBirth(new FlexibleDate(createInteger(v[i++]),
//                    createInteger(v[i++]), createInteger(v[i++])));
//            
//            westernDetails.setPlaceOfBirth(v[i++]);
//            person.setWesternDetails(westernDetails);
//
//
//            String nationalityString = v[i++];
//            if (StringUtils.isNotBlank(nationalityString)) {
//                Nationality nationality = genericDao.getUnique(Nationality.class, "name", nationalityString);
//                if (nationality == null) {
//                    nationality = new Nationality();
//                    nationality.setName(nationalityString);
//                    genericDao.save(nationality);
//                }
//                person.setNationality(nationality);
//            }
//
//            String stalagString = v[i++];
//            if (StringUtils.isNotBlank(stalagString)) {
//                Stalag stalag = genericDao.getUnique(Stalag.class, "name", stalagString);
//                if (stalag == null) {
//                    stalag = new Stalag();
//                    stalag.setName(stalagString);
//                    genericDao.save(stalag);
//                }
//                person.setStalag(stalag);
//            }
//
//            person.setPrisonerNumber(createInteger(v[i++]));
//            
//            String rankString = v[i++];
//            if (StringUtils.isNotBlank(rankString)) {
//                Rank rank = genericDao.getUnique(Rank.class, "name", rankString);
//                if (rank == null) {
//                    rank = new Rank(rankString);
//                    genericDao.save(rank);
//                }
//                person.setRank(rank);
//            }
//            
//            i++;  // pårørende
//
//            person.setDateOfDeath(new FlexibleDate(createInteger(v[i++]),
//                    createInteger(v[i++]), createInteger(v[i++])));
//
//            person.setPlaceOfDeath(v[i++]);
//
////            String cause = v[i++];
//            person.setCausesOfDeath(getCauses(v[i++]));
////            if (StringUtils.isNotEmpty(cause)) {
////                CauseOfDeath causeOfDeath = new CauseOfDeath();
////                causeOfDeath.setCause(cause);
////                person.getCausesOfDeath().add(causeOfDeath);
////            }
//
//            String campString = v[i++];
//            if (StringUtils.isNotBlank(campString)) {
//                Camp camp = genericDao.getUnique(Camp.class, "name", campString);
//                if (camp == null) {
//                    camp = new Camp();
//                    camp.setName(campString);
//                    genericDao.save(camp);
//                }
//                person.setCamp(camp);
//            }
//            
//            String cemeteryString = v[i++];
//            
//            i++; // row - not much there
//            
//            String graveNumber = v[i++];
//            String graveDateString = v[i++];
//            Date graveDate = null;
//            
//            if (StringUtils.isNotBlank(cemeteryString) || StringUtils.isNotBlank(graveNumber) ||
//                    StringUtils.isNotBlank(graveDateString)) {
//                try {
//                    if (StringUtils.isNotBlank(graveDateString)) {
//                        graveDate = dateFormat.parse(graveDateString);
//                    }
//                } catch (java.text.ParseException e) {
//                    // ignore parse errors for now - this is just to get some data
//                }
//    
//                Grave grave = new Grave();
//                grave.setGraveNumber(graveNumber);
//                if (graveDate != null) {
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(graveDate);
//                    grave.getDateOfBurial().setDay(cal.get(Calendar.DAY_OF_MONTH));
//                    grave.getDateOfBurial().setMonth(cal.get(Calendar.MONTH) + 1);
//                    grave.getDateOfBurial().setYear(cal.get(Calendar.YEAR));
//                }
//    
//                if (StringUtils.isNotBlank(cemeteryString)) {
//                    Cemetery cemetery = genericDao.getUnique(Cemetery.class, "name", cemeteryString);
//                    if (cemetery == null) {
//                        cemetery = new Cemetery();
//                        cemetery.setName(cemeteryString);
//                        genericDao.save(cemetery);
//                    }
//    
//                    grave.setCemetery(cemetery);
//                }
//                
//                genericDao.save(grave);
//                person.getGraves().add(grave);
//            }
//
//            savePerson(person);
//        }
//    }

    /**
     * Get causes from a semicolon-separated string.
     * 
     * @param str
     * @return
     */
    private Set<CauseOfDeath> getCauses(String str) {

        if (StringUtils.isNotEmpty(str)) {
            Set<CauseOfDeath> causes = new TreeSet<CauseOfDeath>(new CauseOfDeathComparator());

            for (String causeStr : StringUtils.split(str, ';')) {
                String formattedCauseStr = StringUtils.trimToNull(causeStr);
                if (formattedCauseStr != null) {
                    CauseOfDeath cause = genericDao.getUnique(CauseOfDeath.class, "name", formattedCauseStr);
                    if (cause == null) {
                        cause = new CauseOfDeath();
                        cause.setName(formattedCauseStr);
                    }

                    causes.add(cause);
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

    private static boolean createBoolean(String str) {
        return "1".equals(str);
    }



    /**
     * Handle a single CSV line.
     */
    private class Line {

        private int pos = 0;
        private String[] data = new String[0];

        public Line(String text) throws IOException {
            this.data = CSVUtils.parseLine(text);
        }

        /**
         * @return The current string.
         */
        public String peek() {
            if (pos < data.length) {
                return data[pos];
            } else {
                return "";
            }
        }

        /**
         * @return The current string, and move the pointer one forward.
         */
        public String next() {
            int curPos = pos;
            pos++;

            if (curPos < data.length) {
                return data[curPos];
            } else {
                return "";
            }
        }

        /**
         * @return true if there is more data.
         */
        public boolean hasNext() {
            if (pos < data.length) {
                return true;
            } else {
                return false;
            }
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        Line line = new PersonDao().new Line("ost,bacon,,blubb,,,");
        
        for (int i = 0; i < 20; i++) {
            System.out.println("--" + line.next());
        }

        
//        while (line.hasNext()) {
//            System.out.println("--" + line.next());
//        }
    }
}
