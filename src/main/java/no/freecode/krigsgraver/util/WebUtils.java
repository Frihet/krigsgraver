/**
 *  Project: Krigsgraver
 *  Created: Mar 30, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.util;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import no.freecode.krigsgraver.model.Geolocational;
import no.freecode.krigsgraver.model.NamedEntity;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;

/**
 * Static methods for handling {@link Geolocational} stuff.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
public abstract class WebUtils {

    private static final Logger logger = Logger.getLogger(WebUtils.class);

    public static void mergeEntity(long fromId, long toId, Class<? extends NamedEntity> clazz, String tableName, String columnName,
                GenericDao genericDao, MessageSource messageSource, HttpSession session, Locale locale) {

        if (fromId != toId) {
            NamedEntity from = genericDao.get(clazz, fromId);
            String fromName = from.getName();
            NamedEntity to = genericDao.get(clazz, toId);
            String toName = to.getName();
    
            int affectedEntities = genericDao.replaceOccurrencesOf(tableName, columnName, from, to);
    
            // Should be no references left, so delete the entity.
            genericDao.delete(from);

            logger.info(clazz.getName() + " - Merged " + affectedEntities + " occurrences of " + from.getName() + " (" + from.getId() + ") with " +
                    to.getName() + " (" + to.getId() + ")");
    
            String message = messageSource.getMessage("info.successfullyMerged",
                    new Object[] { affectedEntities, fromName, toName }, locale);
            session.setAttribute("standardInfo", message);

        } else {
            logger.warn("Tried to merge entity with self");
        }
    }

}
