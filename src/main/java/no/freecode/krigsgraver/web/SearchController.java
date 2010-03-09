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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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
import org.springframework.context.MessageSource;
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
    
    @Autowired
    private MessageSource messageSource;


    /**
     * Do a free text search.
     * 
     * @throws ParseException 
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/"})
    public String simpleSearch(
                @RequestParam(value = "q", required = false) String queryString,
                @RequestParam(required = false, defaultValue = "false") boolean simple,
                @RequestParam(value = "page", required = false) Integer page,
                Locale locale,
                Model model) throws ParseException {

        String formattedQueryString = queryString;

        if (simple) {
            formattedQueryString = QueryUtils.makeFuzzy(formattedQueryString);
        }

        if (StringUtils.isBlank(queryString)) {
            return "simple_search";

        } else {
            try {
                Paginator paginator = new Paginator(page);
                model.addAttribute("persons", personDao.search(formattedQueryString, paginator));
                model.addAttribute("paginator", paginator);
                model.addAttribute("simple", simple);
                model.addAttribute("q", queryString);
                return "results";

            } catch (org.apache.lucene.queryParser.ParseException e) {
                model.addAttribute("standardError",
                        messageSource.getMessage("search.invalidInput", new Object[] {formattedQueryString}, locale));
                return "simple_search";
            }
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
                @RequestParam(value = "fuzzyFields", required = false) ArrayList<String> fuzzyFields,
                @RequestParam(required = false) String fullName,
                @RequestParam(required = false) String firstName,
                @RequestParam(required = false) String nameOfFather,
                @RequestParam(required = false) String lastName,
                @RequestParam(required = false) String placeOfBirth,
                @RequestParam(required = false) Integer dateOfBirthFromYear,
                @RequestParam(required = false) Integer dateOfBirthToYear,
                @RequestParam(required = false) Integer dateOfBirthFromMonth,
                @RequestParam(required = false) Integer dateOfBirthToMonth,
                @RequestParam(required = false) Integer dateOfBirthFromDay,
                @RequestParam(required = false) Integer dateOfBirthToDay,
                @RequestParam(required = false) String placeOfDeath,
                @RequestParam(required = false) Integer dateOfDeathFromYear,
                @RequestParam(required = false) Integer dateOfDeathToYear,
                @RequestParam(required = false) Integer dateOfDeathFromMonth,
                @RequestParam(required = false) Integer dateOfDeathToMonth,
                @RequestParam(required = false) Integer dateOfDeathFromDay,
                @RequestParam(required = false) Integer dateOfDeathToDay,
                @RequestParam(required = false) String countryCode,
                Model model) throws ParseException, UnsupportedEncodingException {

        QueryBuilder query = new QueryBuilder(fuzzyFields);
        
        query.append("fullName", fullName);
        query.append("firstName", firstName);
        query.append("nameOfFather", nameOfFather);
        query.append("lastName", lastName);
        query.append("placeOfBirth", placeOfBirth);
        query.appendDateInterval("dateOfBirth", dateOfBirthFromYear, dateOfBirthFromMonth, dateOfBirthFromDay, dateOfBirthToYear, dateOfBirthToMonth, dateOfBirthToDay);
        query.append("nationality.countryCode", countryCode);
        query.append("placeOfDeath", placeOfDeath);
        query.appendDateInterval("dateOfDeath", dateOfDeathFromYear, dateOfDeathFromMonth, dateOfDeathFromDay, dateOfDeathToYear, dateOfDeathToMonth, dateOfDeathToDay);

        return "redirect:/?q=" + URLEncoder.encode(query.getQueryString(), "utf-8");
    }

    private class QueryBuilder {
        
        GregorianCalendar calendar = new GregorianCalendar();
        StringBuilder query = new StringBuilder();
        private boolean firstEntry = true;
        private List<String> fuzzyFields;

        public QueryBuilder(List<String> fuzzyFields) {
            this.fuzzyFields = fuzzyFields;
        }

        private void appendString(String string) {
            if (firstEntry) {
                firstEntry = false;
            } else {
                query.append(" ");
            }
            query.append("+");
//                query.append("(");
            query.append(string);
//                query.append(")");
        }

        public void append(String fieldName, String text) {
            if (StringUtils.isNotBlank(text)) {
                appendString(QueryUtils.formatQuery(text, fieldName, fuzzyFields.contains(fieldName)));
            }
        }

        public void appendDateInterval(
                    String dateField,
                    Integer fromYear, Integer fromMonth, Integer fromDay,
                    Integer toYear, Integer toMonth, Integer toDay) {

            if (fromYear != null || toYear != null) {
                StringBuilder q = new StringBuilder();
                q.append(dateField + ".year:[");
                q.append(fromYear != null ? String.format("%04d", fromYear) : "0000");
                q.append(" TO ");
                q.append(toYear != null ? String.format("%04d", toYear) : calendar
                        .get(Calendar.YEAR));
                q.append("]");
                appendString(q.toString());
            }

            if (fromMonth != null || toMonth != null) {
                StringBuilder q = new StringBuilder();
                q.append(dateField + ".month:[");
                q.append(fromMonth != null ? String.format("%02d", fromMonth) : "00");
                q.append(" TO ");
                q.append(toMonth != null ? String.format("%02d", toMonth) : calendar
                        .get(Calendar.MONTH) + 1);
                q.append("]");
                appendString(q.toString());
            }

            if (fromDay != null || toDay != null) {
                StringBuilder q = new StringBuilder();
                q.append(dateField + ".day:[");
                q.append(fromDay != null ? String.format("%02d", fromDay) : "00");
                q.append(" TO ");
                q.append(toDay != null ? String.format("%02d", toDay) : calendar
                        .get(Calendar.DAY_OF_MONTH));
                q.append("]");
                appendString(q.toString());
            }
        }
        
        public String getQueryString() {
            return query.toString();
        }
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
