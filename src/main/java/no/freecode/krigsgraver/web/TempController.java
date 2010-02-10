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

    

//    /**
//     * Do a free text search.
//     * 
//     * @throws ParseException 
//     */
//    @RequestMapping(method = RequestMethod.GET, value = "/search")
//    public String search(@RequestParam(value = "q", required = false) String queryString,  Model model) throws ParseException {
//
//        if (queryString != null) {
//            model.addAttribute("persons", personDao.findPersons(queryString));
//        }
//        
//        return "search";
//    }
//    
}
