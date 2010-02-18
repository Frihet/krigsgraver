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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import no.freecode.krigsgraver.model.CauseOfDeath;
import no.freecode.krigsgraver.model.Cemetery;
import no.freecode.krigsgraver.model.Grave;
import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.dao.GenericDao;
import no.freecode.krigsgraver.model.dao.PersonDao;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute("command", new PersonCommandObject(new Person()));
        return "person/edit";
    }

    /**
     * Edit an existing person.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{personId}/edit")
    public String getEditForm(@PathVariable int personId, Model model) {
        model.addAttribute("command", new PersonCommandObject(personDao.getPerson(personId)));
        return "person/edit";
    }

    /**
     * Get an existing person.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{personId}")
    public Person getPerson(@PathVariable int personId) {
        return personDao.getPerson(personId);
    }


//    @RequestMapping(method = RequestMethod.POST, value = "*/edit")
//    public String save(@Valid Person person, BindingResult result) {
//        if (result.hasErrors()) {
//            return "person/edit";
//        }
//
//        personDao.savePerson(person);
//        return "redirect:/person/list";
//    }

    /**
     * Submit a new person.
     */
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(@Valid @ModelAttribute("command") PersonCommandObject command, BindingResult result, Model model) {

        for (Grave grave : command.getLazyGraves()) {
            logger.debug("Got grave: " + ReflectionToStringBuilder.toString(grave));
        }

        if (result.hasErrors()) {
            return "person/edit";
        }

        personDao.savePersonCommandObject(command);
        
        return "redirect:/person/list";
//        return "redirect:/person/" + person.getId();
    }

//    /**
//     * Submit a new person.
//     */
//    @RequestMapping(method = RequestMethod.POST, value = {"create"})
//    public String create(@Valid Person person, BindingResult result) {
//        if (result.hasErrors()) {
//            return "person/edit";
//        }
//
//        personDao.savePerson(person);
//        return "redirect:/person/list";
////        return "redirect:/person/" + person.getId();
//    }

    @ModelAttribute("causesOfDeath")
    public Collection<CauseOfDeath> getCausesOfDeath() {
        return genericDao.getAll(CauseOfDeath.class);
    }

    @ModelAttribute("cemeteries")
    public Collection<Cemetery> getCausesCemeteries() {
        return genericDao.getAll(Cemetery.class);
    }
    
    /**
     * Upload people.
     */
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
    @RequestMapping(method = RequestMethod.GET, value = "search")
    public String search(@RequestParam(value = "q", required = false) String queryString,  Model model) throws ParseException {

        if (queryString != null) {
            model.addAttribute("persons", personDao.findPersons(queryString));
        }
        
        return "person/search";
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<Grave> list = LazyList.decorate(new ArrayList<Grave>(), new Factory() {
            @Override
            public Object create() {
                return new Grave();
            }
        });

        System.out.println(list.get(100));

        for (Grave g : list) {
            System.out.println("- " + g);
        }
    }
}
