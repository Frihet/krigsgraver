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

import no.freecode.krigsgraver.model.dao.PersonDao;

import org.springframework.beans.factory.annotation.Autowired;
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
public class TempController {

    @Autowired
    private PersonDao personDao;


    /**
     * Index all the data in the search engine.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/admin/indexData")
    public void indexData() {

        personDao.indexData();
        
    }
    

        
}
