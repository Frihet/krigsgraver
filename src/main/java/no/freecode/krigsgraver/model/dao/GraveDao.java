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

import no.freecode.krigsgraver.model.Grave;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data access methods for Grave entities.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Repository
@Transactional
public class GraveDao {

    private static final Logger logger = Logger.getLogger(GraveDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    
    /**
     * Update or create the grave.
     */
    public void saveGrave(Grave grave) {
        sessionFactory.getCurrentSession().merge(grave);
    }
    
    /**
     * Fetch a grave from the DB.
     */
    @Transactional(readOnly = true)
    public Grave getGrave(long id) {
        return (Grave) sessionFactory.getCurrentSession().get(Grave.class, id);
    }

    
}
