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

import no.freecode.krigsgraver.model.PostalDistrict;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data access methods for PostalDistrict entities.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Repository
@Transactional
public class PostalDistrictDao {

    private static final Logger logger = Logger.getLogger(PostalDistrictDao.class);

    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * Update or create the postalDistrict.
     */
    public void savePostalDistrict(PostalDistrict postalDistrict) {
        sessionFactory.getCurrentSession().merge(postalDistrict);
    }

    /**
     * Fetch a postalDistrict from the DB.
     */
    @Transactional(readOnly = true)
    public PostalDistrict get(int postcode) {
        return (PostalDistrict) sessionFactory.getCurrentSession().get(PostalDistrict.class, postcode);
    }
    
    /**
     * Fetch a list of all PostalDistrict objects from the DB.
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<PostalDistrict> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(PostalDistrict.class).list();
    }

    /**
     * Load data from a stream of CSV.
     * 
     * @param inputStream
     * @throws IOException 
     */
    public void loadCsvData(InputStream inputStream) throws IOException {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            if (StringUtils.isNotBlank(line)) {
                line = StringUtils.lowerCase(line);

                logger.debug("Parsing line: " + line);

                PostalDistrict district = new PostalDistrict();

                // postnummer
                district.setPostcode(NumberUtils.toInt(StringUtils.substring(line, 0, 4)));

                // poststed
                String name = WordUtils.capitalize(StringUtils.substring(line, 4, 36), new char[] {' ', '-'});
                name = StringUtils.replaceEach(name, 
                        new String[] {" I ", " På "},
                        new String[] {" i ", " på "});

                district.setName(StringUtils.trimToEmpty(name));

                // fylke
                district.setCountyId(NumberUtils.toInt(StringUtils.substring(line, 36, 38)));

                savePostalDistrict(district);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(WordUtils.capitalize(StringUtils.lowerCase("VANG PÅ HEDMARK"), new char[] {' ', '-'}));
        
        String line = "1592VÅLER I ØSTFOLD                 0137VÅLER                         B";
//1592VÅLER I ØSTFOLD                 0137VÅLER                         B
//1613FREDRIKSTAD                     0106FREDRIKSTAD                   G


        line = StringUtils.lowerCase(line);

        logger.debug("Parsing line: " + line);

        PostalDistrict district = new PostalDistrict();

        // postnummer
        district.setPostcode(NumberUtils.toInt(StringUtils.substring(line, 0, 4)));

        // poststed
        String name = WordUtils.capitalize(StringUtils.substring(line, 4, 36), new char[] {' ', '-'});
        name = StringUtils.replaceEach(name, 
                new String[] {" I ", " På "},
                new String[] {" i ", " på "});

        district.setName(StringUtils.trimToEmpty(name));

        // fylke
        district.setCountyId(NumberUtils.toInt(StringUtils.substring(line, 36, 38)));
        
        logger.debug("result: " + ReflectionToStringBuilder.toString(district));
    }

}
