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

import java.util.HashMap;
import java.util.Locale;

import no.freecode.krigsgraver.model.InfoPage;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 * Data access methods for {@link InfoPage}s (help pages editable by the admin users).
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Repository
@Transactional
public class InfoPageDao {

    private static final Logger logger = Logger.getLogger(InfoPageDao.class);

    // We'll cache all pages in this map.
    private static HashMap<String, InfoPage> infoPages = new HashMap<String, InfoPage>();

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Update or create an {@link InfoPage} in the DB.
     */
    public void saveInfoPage(InfoPage infoPage) {
        sessionFactory.getCurrentSession().merge(infoPage);
        infoPages.put(infoPage.getPageName() + "_" + infoPage.getLanguage(), infoPage);
    }

    /**
     * Fetch an {@link InfoPage} from the DB, or from the local cache (preferably).
     */
    @Transactional(readOnly = true)
    public InfoPage getInfoPage(String pageName, String language) {

        String pageId = pageName + "_" + language;
        
        if (infoPages.containsKey(pageId)) {
            return infoPages.get(pageId);

        } else {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(InfoPage.class)
                .add(Restrictions.eq("pageName", pageName))
                .add(Restrictions.eq("language", language));

            InfoPage infoPage = (InfoPage) criteria.uniqueResult();
            infoPages.put(pageId, infoPage);
            
            return infoPage;
        }
    }

    public void prepareModel(Model model, String pageName, Locale locale) {
        model.addAttribute("infoPageName", pageName);
        model.addAttribute("infoPage", getInfoPage(pageName, locale.getLanguage()));
    }
}
