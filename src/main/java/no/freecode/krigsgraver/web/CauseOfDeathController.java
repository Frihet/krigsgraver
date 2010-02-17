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

import java.util.List;

import javax.validation.Valid;

import no.freecode.krigsgraver.model.CauseOfDeath;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Edit and display {@link CauseOfDeath} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/person/causeOfDeath")
public class CauseOfDeathController {

    @Autowired
    private GenericDao genericDao;

    /**
     * List all the people.
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public @ResponseBody List<CauseOfDeath> getList() {
        return genericDao.getAll(CauseOfDeath.class, Order.asc("cause"));
    }

    /**
     * List all the people.
     */
    @RequestMapping(method = RequestMethod.GET, value = "causeList")
//    public @ResponseBody List<String> getIdList() {
    public List<String> getCauseList() {
//        return genericDao.getAll(CauseOfDeath.class);
        return genericDao.test();
    }

    /**
     * Create a new {@link CauseOfDeath}.
     */
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute(new CauseOfDeath());
        return "person/causeOfDeath/edit";
    }

    /**
     * Submit a new {@link CauseOfDeath}.
     */
    @RequestMapping(method = RequestMethod.POST, value = {"create"})
    public String createNew(@Valid CauseOfDeath causeOfDeath, BindingResult result) {

        if (result.hasErrors()) {
            return "person/causeOfDeath/edit";
        }

        if (genericDao.containsEntry(CauseOfDeath.class, "cause", causeOfDeath.getCause())) {
            result.rejectValue("cause", "errors.entry_already_exists");
            return "person/causeOfDeath/edit";
        }
        
        genericDao.save(causeOfDeath);

        return "success";
    }

    /**
     * Edit an existing {@link CauseOfDeath}.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}/edit")
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute(genericDao.get(CauseOfDeath.class, id));
        return "person/causeOfDeath/edit";
    }

    /**
     * Save {@link CauseOfDeath}.
     */
    @RequestMapping(method = RequestMethod.POST, value = "*/edit")
    public String save(@Valid CauseOfDeath causeOfDeath, BindingResult result) {
        if (result.hasErrors()) {
            return "person/causeOfDeath/edit";
        }

        genericDao.save(causeOfDeath);
        return "redirect:/person/list";  // TODO: handle success
    }
}
