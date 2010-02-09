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

import javax.validation.Valid;

import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.dao.PersonDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Edit and display Person objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonDao personDao;

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
        model.addAttribute(new Person());
        return "person/edit";
    }

    /**
     * Edit an existing person.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{personId}/edit")
    public String getEditForm(@PathVariable int personId, Model model) {
        model.addAttribute(personDao.getPerson(personId));
        return "person/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "*/edit")
    public String save(@Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "person/edit";
        }

        personDao.savePerson(person);
        return "redirect:/person/list";
//        return "redirect:/person/" + person.getId();
    }

    
    /**
     * Submit a new person.
     */
    @RequestMapping(method = RequestMethod.POST, value = {"create"})
    public String create(@Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "person/edit";
        }

        personDao.savePerson(person);
        return "redirect:/person/list";
//        return "redirect:/person/" + person.getId();
    }

    /**
     * Create a new person.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{personId}")
    public String getViewForm(@PathVariable int personId, Model model) {
        model.addAttribute(personDao.getPerson(personId));
        return "person/view";
    }


    // /**
    // *
    // */
    // @RequestMapping(method = RequestMethod.GET, value = "/person/{personId}")
    // public void showPerson(@PathVariable int personId) {
    // // TODO Auto-generated method stub
    // }
    //    
    // /**
    // *
    // */
    // @RequestMapping(method = RequestMethod.GET, value =
    // "/person/{personId}/edit.do")
    // public void editPerson(@PathVariable int personId) {
    // // TODO Auto-generated method stub
    // }

    // @RequestMapping(method = RequestMethod.POST, value = "/person/save.do")
    // public void submitPerson() {
    // // TODO Auto-generated method stub
    // }

    // private Map<Long, Account> accounts = new ConcurrentHashMap<Long,
    // Account>();
    //    
    // @RequestMapping(method=RequestMethod.GET)
    // public String getCreateForm(Model model) {
    // model.addAttribute(new Account());
    // return "account/createForm";
    // }
    //    
    // @RequestMapping(method=RequestMethod.POST)
    // public String create(@Valid Account account, BindingResult result) {
    // if (result.hasErrors()) {
    // return "account/createForm";
    // }
    // this.accounts.put(account.assignId(), account);
    // return "redirect:/account/" + account.getId();
    // }
    //    
    // @RequestMapping(value="{id}", method=RequestMethod.GET)
    // public String getView(@PathVariable Long id, Model model) {
    // Account account = this.accounts.get(id);
    // if (account == null) {
    // throw new ResourceNotFoundException(id);
    // }
    // model.addAttribute(account);
    // return "account/view";
    // }

}
