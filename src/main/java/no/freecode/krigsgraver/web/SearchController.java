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

import no.freecode.krigsgraver.model.dao.GenericDao;
import no.freecode.krigsgraver.model.dao.Paginator;
import no.freecode.krigsgraver.model.dao.PersonDao;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Edit and display Person objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
public class SearchController {

    private static final Logger queryLogger = Logger.getLogger("queryLog");
    private static final Logger logger = Logger.getLogger(SearchController.class);

    @Autowired
    private PersonDao personDao;

    @Autowired
    private GenericDao genericDao;


    /**
     * Do a free text search.
     * 
     * @throws ParseException 
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/"})
    public String simpleSearch(
                @RequestParam(value = "q", required = false) String queryString,
                @RequestParam(value = "page", required = false) Integer page,
                Model model) throws ParseException {

        if (StringUtils.isBlank(queryString)) {
            return "simple_search";

        } else {
            Paginator paginator = new Paginator(page);
            model.addAttribute("persons", personDao.search(queryString, paginator));
            model.addAttribute("paginator", paginator);
            return "results";
        }
        
    }
}
