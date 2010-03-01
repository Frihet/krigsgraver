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
import javax.validation.Valid;

import no.freecode.krigsgraver.model.Camp;
import no.freecode.krigsgraver.model.CauseOfDeath;
import no.freecode.krigsgraver.model.Cemetery;
import no.freecode.krigsgraver.model.Nationality;
import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.Rank;
import no.freecode.krigsgraver.model.Stalag;
import no.freecode.krigsgraver.model.dao.GenericDao;
import no.freecode.krigsgraver.model.dao.PersonDao;

import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Edit and display Person objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {

    private static final Logger logger = Logger.getLogger(PersonController.class);

    @Autowired
    private PersonDao personDao;

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private MessageSource messageSource;

    /**
     * List all the people.
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public String getListForm(Model model) {
        model.addAttribute("persons", personDao.getAll());
        return "person/list";
    }

    /**
     * Create a new person.
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute("nationalities", genericDao.getAll(Nationality.class, Order.asc("countryCode")));
        model.addAttribute("command", new PersonCommandObject(new Person()));
        return "person/edit";
    }

    /**
     * Edit an existing person.
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.GET, value = "{personId}/edit")
    public String getEditForm(@PathVariable long personId, Model model) {
        model.addAttribute("nationalities", genericDao.getAll(Nationality.class, Order.asc("countryCode")));
        model.addAttribute("command", new PersonCommandObject(personDao.getPerson(personId)));
        return "person/edit";
    }

    /**
     * Get an existing person.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{personId}/view")
    public String getPerson(@PathVariable long personId, Model model) {
        model.addAttribute("person", personDao.getPerson(personId));
        return "person/view";
    }
    
    /**
     * Submit a new person.
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(@Valid @ModelAttribute("command") PersonCommandObject command, BindingResult result, Model model, HttpSession session, Locale locale) {

        if (result.hasErrors()) {
            return "person/edit";
        }

        personDao.savePersonCommandObject(command);

        String message = messageSource.getMessage("person.successfullySaved",
                new Object[] { command.getPerson().toString() }, locale);
        session.setAttribute("standardInfo", message);

        return "redirect:/person/" + command.getPerson().getId() + "/edit";
    }


//    @ModelAttribute("nationalities")
//    public List<Nationality> getNationalities() {
//        return genericDao.getAll(Nationality.class, Order.asc("countryCode"));
//    }


    /**
     * Upload people.
     */
    // TODO: secure
//    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.GET, value = "upload")
    public String getUploadForm() {
        return "person/upload";
    }

    /**
     * Upload a batch of people.
     * 
     * @throws IOException 
     */
    @RequestMapping(method = RequestMethod.POST, value = "upload")
    // TODO: secure
//    @Secured({"ROLE_ADMIN"})
    public String handleUpload(@RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            personDao.loadCsvData(file.getInputStream());
            return "redirect:/person/list";
            
        } else {
            return "redirect:uploadFailure";
        }
    }

    /**
     * Do a free text search.
     * 
     * @throws ParseException 
     */
//    @RequestMapping(method = RequestMethod.GET, value = "search")
//    public String search(@RequestParam(value = "q", required = false) String queryString,  Model model) throws ParseException {
//
//        if (queryString != null) {
//            model.addAttribute("persons", personDao.search(queryString));
//        }
//        
//        return "person/search";
//    }

    private static void validatePerson(Person person, Errors errors) {
        if (person.getCyrillicDetails().isEmpty() && person.getWesternDetails().isEmpty()) {
            errors.reject("person.error.missingName");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        logger.debug("Initializing custom editors for the entities.");
        
        dataBinder.registerCustomEditor(Stalag.class, new EntityPropertyEditor(Stalag.class, genericDao));
        dataBinder.registerCustomEditor(Camp.class, new EntityPropertyEditor(Camp.class, genericDao));
        dataBinder.registerCustomEditor(Nationality.class, new EntityPropertyEditor(Nationality.class, genericDao));
        dataBinder.registerCustomEditor(Rank.class, new EntityPropertyEditor(Rank.class, genericDao));
        dataBinder.registerCustomEditor(Cemetery.class, new EntityPropertyEditor(Cemetery.class, genericDao));
        dataBinder.registerCustomEditor(CauseOfDeath.class, new EntityPropertyEditor(CauseOfDeath.class, genericDao));
    }
}
