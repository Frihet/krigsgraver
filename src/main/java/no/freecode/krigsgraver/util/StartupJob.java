/**
 *  Project: Krigsgraver
 *  Created: Mar 17, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.util;

import no.freecode.krigsgraver.model.User;
import no.freecode.krigsgraver.model.User.Role;
import no.freecode.krigsgraver.model.dao.UserDao;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * This job is run once when starting the application. 
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
public class StartupJob {
    
    private static final Logger logger = Logger.getLogger(StartupJob.class);

//    private PersonDao personDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean actualFactory;


    public void execute() {
        insertBaseUser();
        dumpSchema();
    }

    /**
     * Create an admin user if there are no other users on the system.
     */
    private void insertBaseUser() {
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
            logger.info("Not creating initial admin user, as there are already " + numberOfUsers + " other users on the system.");
        }
    }

    private void dumpSchema() {
        Configuration configuration = actualFactory.getConfiguration();
        SchemaExport export = new SchemaExport(configuration);
//        export.setOutputFile("schema.sql");
        export.create(true, false);
        
//        new org.apache.log4j.FileAppender().set
    }

}
