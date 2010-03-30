/**
 *  Project: Krigsgraver
 *  Created: Mar 17, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.search;

import no.freecode.krigsgraver.model.dao.PersonDao;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * A {@link Job} that is used to regularly update the search index.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
public class IndexJob implements Job {
    
    private static final Logger logger = Logger.getLogger(IndexJob.class);

    private PersonDao personDao;

    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Updating search index - START.");
        personDao.indexData();
        logger.info("Updating search index - STOP.");
    }

    public PersonDao getPersonDao() {
        return personDao;
    }
    
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
