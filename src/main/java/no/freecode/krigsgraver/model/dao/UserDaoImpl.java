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

import no.freecode.krigsgraver.model.User;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data access methods for Cemetery entities.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao, UserDetailsService {

//    private static final Logger logger = Logger.getLogger(UserDao.class);
//    private static final Logger userLogger = Logger.getLogger("userLog");

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional(readOnly = true)
    public User getUser(int id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Transactional(readOnly = true)
//    public List<User> getUsers(Paginator paginator) {
    public List<User> getUsers() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.addOrder(Order.asc("username"));

//        criteria.setFirstResult(Paginator.ITEMS_PER_PAGE * (paginator.getPageNumber() - 1));
//        criteria.setMaxResults(Paginator.ITEMS_PER_PAGE + 1);

        @SuppressWarnings("unchecked")
        List<User> results = (List<User>) criteria.list();
//        return paginator.paginate(results);
        
        return results;
    }
    
    /* (non-Javadoc)
     * @see no.freecode.krigsgraver.model.dao.UserDao#getNumberOfUsers()
     */
    @Override
    public long getNumberOfUsers() {
        Query q = sessionFactory.getCurrentSession().createQuery("select count(*) from KgUser");
        return (Long) q.uniqueResult();
    }

    public void saveUser(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }

    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    /* (non-Javadoc)
     * @see org.springframework.security.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.ilike("username", username, MatchMode.EXACT));
        User user = (User) criteria.uniqueResult();

        if (user != null) {
//            userLogger.info("User " + user + " logged on.");

        } else {
//            userLogger.warn("Tried to log in with invalid username: '" + username + "'.");
            throw new UsernameNotFoundException("Unable to load user '" + username + "'");
        }
        
        return user;
    }
}
