/**
 *  Project: Krigsgraver
 *  Created: Feb 8, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.web;

import java.io.IOException;

import javax.servlet.ServletResponse;

import no.freecode.krigsgraver.model.User;
import no.freecode.krigsgraver.model.User.Role;
import no.freecode.krigsgraver.model.dao.GenericDao;
import no.freecode.krigsgraver.model.dao.PersonDao;
import no.freecode.krigsgraver.model.dao.PostalDistrictDao;
import no.freecode.krigsgraver.model.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Do free text searches.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
//@RequestMapping(value = "/search")
public class BaseController {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private PostalDistrictDao postalDistrictDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Index all the data in the search engine.
     * @throws IOException 
     */
    @RequestMapping(method = RequestMethod.GET, value = "/admin/indexData")
    public void indexData(ServletResponse response) throws IOException {
        personDao.indexData();
        response.getWriter().println("Updated search index.");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/insertUser")
    public String insertUser() {

        User user = new User();
        user.setName("Reidar Øksnevad");
        user.setUsername("reidar");
        user.setRole(Role.ROLE_ADMIN);
//        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        user.setPassword(passwordEncoder.encodePassword("kg", null));
        userDao.saveUser(user);

        user = new User();
        user.setName("KG");
        user.setUsername("kg");
        user.setRole(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encodePassword("kg", null));
        userDao.saveUser(user);

        return "welcome";
    }
}
