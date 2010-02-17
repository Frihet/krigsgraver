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

import no.freecode.krigsgraver.model.Cemetery;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data access methods for Cemetery entities.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Repository
@Transactional
public class CemeteryDao {

    private static final Logger logger = Logger.getLogger(CemeteryDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    
    /**
     * Update or create the cemetery.
     */
    public void saveCemetery(Cemetery cemetery) {
        sessionFactory.getCurrentSession().merge(cemetery);
    }
    
    /**
     * Fetch a cemetery from the DB.
     */
    @Transactional(readOnly = true)
    public Cemetery getCemetery(long id) {
        return (Cemetery) sessionFactory.getCurrentSession().get(Cemetery.class, id);
    }

    
}
