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
import java.util.Locale;

import javax.servlet.http.HttpSession;

import no.freecode.krigsgraver.model.User;
import no.freecode.krigsgraver.model.User.Role;
import no.freecode.krigsgraver.model.dao.GenericDao;
import no.freecode.krigsgraver.model.dao.PersonDao;
import no.freecode.krigsgraver.model.dao.PostalDistrictDao;
import no.freecode.krigsgraver.model.dao.UserDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
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
public class BaseController {

    private static final Logger logger = Logger.getLogger(BaseController.class);
    
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

    @Autowired
    private MessageSource messageSource;

    
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.GET, value = "/admin/various")
    public String getVariousPage() {
        return "admin/various";
    }

    /**
     * Index all the data in the search engine.
     * @throws IOException 
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.GET, value = "/admin/indexData")
    public String indexData(HttpSession session, Locale locale) throws IOException {
        long time = System.currentTimeMillis();
        personDao.indexData();
        
        String message = messageSource.getMessage("admin.various.updateIndexSuccess",
                new Object[] { "" + ((System.currentTimeMillis() - time) / 1000) }, locale);
        session.setAttribute("standardInfo", message);
        
        return "redirect:/admin/various";
//        response.getWriter().println("Updated search index.");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/createAdminUser")
    public String insertBaseUser() {
        String defaultAdminUser = "kg";
        String defaultAdminPassword = "kg";

        long numberOfUsers = userDao.getNumberOfUsers();

        if (numberOfUsers == 0) {
            logger.info("Creating initial '" + defaultAdminUser + "' user with password '" + defaultAdminPassword + "'.");

            User user = new User();
            user.setName("Site administrator");
            user.setUsername(defaultAdminUser);
            user.setRole(Role.ROLE_ADMIN);
            user.setPassword(passwordEncoder.encodePassword(defaultAdminPassword, null));
            userDao.saveUser(user);

        } else {
            logger.warn("NOT creating initial admin user, as there are already " + numberOfUsers + " other users on the system.");
        }

        return "redirect:/";
    }
}
