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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import no.freecode.krigsgraver.model.Nationality;
import no.freecode.krigsgraver.model.dao.GenericDao;
import no.freecode.krigsgraver.model.dao.Paginator;
import no.freecode.krigsgraver.model.dao.PersonDao;
import no.freecode.search.QueryUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
                @RequestParam(required = false, defaultValue = "false") boolean fuzzy,
                @RequestParam(value = "page", required = false) Integer page,
                Model model) throws ParseException {

        String formattedQueryString = queryString;

        if (fuzzy) {
            formattedQueryString = QueryUtils.makeFuzzy(formattedQueryString);
        }

        if (StringUtils.isBlank(queryString)) {
            return "simple_search";

        } else {
            Paginator paginator = new Paginator(page);
            model.addAttribute("persons", personDao.search(formattedQueryString, paginator));
            model.addAttribute("paginator", paginator);
            model.addAttribute("q", queryString);
            return "results";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/queryBuilder"})
    public String queryBuilderGet(Model model) {

        model.addAttribute("nationalities", genericDao.getAll(Nationality.class, Order.asc("countryCode")));
        
        return "advanced_search";
    }

    // ivan krevenkov

    @RequestMapping(method = RequestMethod.POST, value = {"/queryBuilder"})
    public String queryBuilderPost(
//                @RequestParam("params") HashMap<String, String> params,
                @RequestParam(required = false) String fullName,
                @RequestParam(required = false, defaultValue = "false") boolean fuzzyName,
                @RequestParam(required = false) String placeOfBirth,
                @RequestParam(required = false, defaultValue = "false") boolean fuzzyPlaceOfBirth,
                @RequestParam(required = false) Integer dateOfBirthFromYear,
                @RequestParam(required = false) Integer dateOfBirthToYear,
                @RequestParam(required = false) Integer dateOfBirthFromMonth,
                @RequestParam(required = false) Integer dateOfBirthToMonth,
                @RequestParam(required = false) Integer dateOfBirthFromDay,
                @RequestParam(required = false) Integer dateOfBirthToDay,
                @RequestParam(required = false, defaultValue = "") String countryCode,

//                @RequestParam(required = false, defaultValue = "false") Integer fuzzyPlaceOfBirth,
//                dateOfBirth.year", "dateOfBirth.month", "dateOfBirth.day
//                @RequestParam(required = false, defaultValue = "") boolean name,
//                @RequestParam(required = false, defaultValue = "") String placeOfBirth,
//                @RequestParam(required = false) Date dateOfBirthFrom,
//                @RequestParam(required = false) Date dateOfBirthTo,
                Model model) throws ParseException, UnsupportedEncodingException {

        GregorianCalendar calendar = new GregorianCalendar();

        StringBuilder q = new StringBuilder();

        if (StringUtils.isNotBlank(fullName)) {
            q.append(" ");
            q.append(QueryUtils.formatQuery(fullName, "fullName", fuzzyName));
        }

        if (StringUtils.isNotBlank(placeOfBirth)) {
            q.append(" ");
            q.append(QueryUtils.formatQuery(placeOfBirth, "placeOfBirth", fuzzyPlaceOfBirth));
        }
        
        if (dateOfBirthFromYear != null || dateOfBirthToYear != null) {
            q.append(" dateOfBirth.year:[");
            q.append(dateOfBirthFromYear != null ? String.format("%04d", dateOfBirthFromYear): "0");
            q.append(" TO ");
            q.append(dateOfBirthToYear != null ? String.format("%04d", dateOfBirthToYear) : calendar.get(Calendar.YEAR));
            q.append("]");
        }

        if (dateOfBirthFromMonth != null || dateOfBirthToMonth != null) {
            q.append(" dateOfBirth.month:[");
            q.append(dateOfBirthFromMonth != null ? String.format("%02d", dateOfBirthFromMonth): "0");
            q.append(" TO ");
            q.append(dateOfBirthToMonth != null ? String.format("%02d", dateOfBirthToMonth) : calendar.get(Calendar.MONTH) + 1);
            q.append("]");
        }

        if (dateOfBirthFromDay != null || dateOfBirthToDay != null) {
            q.append(" dateOfBirth.day:[");
            q.append(dateOfBirthFromDay != null ? String.format("%02d", dateOfBirthFromDay): "0");
            q.append(" TO ");
            q.append(dateOfBirthToDay != null ? String.format("%02d", dateOfBirthToDay) : calendar.get(Calendar.DAY_OF_MONTH));
            q.append("]");
        }

        if (StringUtils.isNotBlank(countryCode)) {
            q.append(" nationality.countryCode:(");
            q.append(countryCode);
            q.append(")");
        }


//        model.addAttribute("placeOfBirth", placeOfBirth);
//        model.addAttribute("nationality", nationality);

        return "redirect:/?q=" + URLEncoder.encode(q.toString(), "utf-8");
        // return "advanced_search";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/test2"})
    public String advancedSearch(
                @RequestParam(value = "page", required = false) Integer page,
                Model model) throws ParseException {
        
        // Restrictions.eq("rank.name", "soldat");
        
        Paginator paginator = new Paginator(page);
        model.addAttribute("persons", personDao.search(paginator));
        model.addAttribute("paginator", paginator);
        return "results";
    }
    
    
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
