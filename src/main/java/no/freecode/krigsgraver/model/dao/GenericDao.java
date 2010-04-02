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

import no.freecode.krigsgraver.model.Entity;
import no.freecode.krigsgraver.model.IndexedEntity;
import no.freecode.krigsgraver.model.Person;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * General data access methods for any {@link Entity}.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Repository
@Transactional
public class GenericDao {

    private static final Logger logger = Logger.getLogger(GenericDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Update or create an object.
     */
    public void save(Object object) {
        sessionFactory.getCurrentSession().merge(object);
    }

    /**
     * @param <T>
     * @param entity
     * @throws org.hibernate.exception.ConstraintViolationException
     */
    public boolean delete(Entity entity) {
        try {
            sessionFactory.getCurrentSession().delete(entity);
            
        } catch (ConstraintViolationException e) {
            logger.warn("Constraint violation while trying to delete entity of type " + entity.getClass() + " with id " + entity.getId());
            return false;
        }
        
        return true;
    }

    /**
     * Replace all occurrences of the 'from' {@link Entity} with the 'to'
     * {@link Entity}, optionally completely removing the object from its DB
     * table.
     * 
     * @param tableName The table to manipulate, e.g. 'Person' or 'Grave'.
     * @param columnName The column name in {@link Person}, e.g. 'stalag' or 'cemetery'.
     * @param from
     * @param to
     * @param deleteFrom Delete the 'from' {@link Entity}.
     * @return The number of rows in {@link Person} that were replaced.
     */
    public int replaceOccurrencesOf(String tableName, String columnName, Entity from, Entity to) {
        Session session = sessionFactory.getCurrentSession();

        Query q = session.createQuery("update " + tableName + " set " + columnName + " = :newValue where " + columnName + " = :oldValue")
                .setEntity("oldValue", from)
                .setEntity("newValue", to);

        return q.executeUpdate();
    }

    public int replaceOccurrencesOfSQL(String tableName, String columnName, long fromId, long toId) {
        Session session = sessionFactory.getCurrentSession();

        Query q = session.createSQLQuery("update " + tableName + " set " + columnName + " = :newValue where " + columnName + " = :oldValue")
                .setLong("oldValue", fromId)
                .setLong("newValue", toId);

        return q.executeUpdate();
    }

    
    /**
     * Fetch an object from the DB.
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public <T> T get(Class<T> clazz, long id) {
        return (T) sessionFactory.getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public <T> T getUnique(Class<T> clazz, String entryName, Object value) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(clazz);
        crit.add(Restrictions.eq(entryName, value));
        return (T) crit.uniqueResult();
    }
    
    @Transactional(readOnly = true)
    public <T> boolean containsEntry(Class<T> clazz, String entryName, Object value) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(clazz);
        crit.add(Restrictions.eq(entryName, value));
        crit.setMaxResults(1);
        return crit.list().size() > 0;
    }

    /**
     * Fetch a list of all objects of a given class from the DB.
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public <T> List<T> getAll(Class<T> clazz, Order... orders) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(clazz);
        
        for (Order order : orders) {
            crit.addOrder(order);
        }
        
        return crit.list();
    }
    
    /**
     * Index all objects of a given class in the search engine.
     */
    public <T extends IndexedEntity> void indexData(Class<T> clazz) {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());

        @SuppressWarnings("unchecked")
        List<T> entityList = sessionFactory.getCurrentSession().createCriteria(clazz).list();

        for (T entity : entityList) {
            fullTextSession.index(entity);
        }
    }
}
