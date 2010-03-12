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
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public String getListForm(Model model) {
        model.addAttribute("persons", personDao.getAll());
        return "person/list";
    }

    /**
     * Create a new person.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute("nationalities", genericDao.getAll(Nationality.class, Order.asc("countryCode")));
        model.addAttribute("command", new PersonCommandObject(new Person()));
        return "person/edit";
    }

    /**
     * Edit an existing person.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "{id}/edit")
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("nationalities", genericDao.getAll(Nationality.class, Order.asc("countryCode")));
        model.addAttribute("command", new PersonCommandObject(personDao.getPerson(id)));
        return "person/edit";
    }

    /**
     * Get an existing person.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}/view")
    public String getPerson(@PathVariable long id, Model model) {
        model.addAttribute("person", personDao.getPerson(id));
        return "person/view";
    }

    /**
     * Submit a person.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(Model model, @Valid @ModelAttribute("command") PersonCommandObject command, BindingResult result, HttpSession session, Locale locale) {

        // A little more validation.
        validatePerson(command.getPerson(), result);
        
        if (result.hasErrors()) {
            return "person/edit";
        }
        
        personDao.savePersonCommandObject(command);

        String message = messageSource.getMessage("person.successfullySaved",
                new Object[] { command.getPerson().toString() }, locale);
        session.setAttribute("standardInfo", message);

        return "redirect:/person/" + command.getPerson().getId() + "/edit";
    }

    /**
     * Upload people.
     */
    // TODO: secure
//    @Secured({"ROLE_ADMIN"})
    @Secured({"ROLE_ADMIN"})
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
    @Secured({"ROLE_ADMIN"})
    public String handleUpload(@RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            personDao.loadCsvData(file.getInputStream());
            return "redirect:/person/list";
            
        } else {
            return "redirect:uploadFailure";
        }
    }

    private static void validatePerson(Person person, Errors errors) {
        if (person.getCyrillicDetails().hasNoName() && person.getWesternDetails().hasNoName()) {
            errors.reject("person.error.missingName");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
//        logger.debug("Initializing custom editors for the entities.");
        dataBinder.registerCustomEditor(Stalag.class, new EntityPropertyEditor(Stalag.class, genericDao));
        dataBinder.registerCustomEditor(Camp.class, new EntityPropertyEditor(Camp.class, genericDao));
        dataBinder.registerCustomEditor(Nationality.class, new EntityPropertyEditor(Nationality.class, genericDao));
        dataBinder.registerCustomEditor(Rank.class, new EntityPropertyEditor(Rank.class, genericDao));
        dataBinder.registerCustomEditor(Cemetery.class, new EntityPropertyEditor(Cemetery.class, genericDao));
        dataBinder.registerCustomEditor(CauseOfDeath.class, new EntityPropertyEditor(CauseOfDeath.class, genericDao));
    }
}
