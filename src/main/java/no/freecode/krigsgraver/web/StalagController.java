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
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import no.freecode.krigsgraver.model.CauseOfDeath;
import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.Stalag;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Edit and display {@link CauseOfDeath} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/stalag")
public class StalagController {

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("stalags")
    public List<Stalag> getStalags() {
        return getList();
    }
    
    /**
     * List all the stalags.
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public List<Stalag> getList() {
        return genericDao.getAll(Stalag.class, Order.asc("name"));
    }

    /**
     * Create a new {@link Stalag}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute("stalag", new Stalag());
        return "stalag/edit";
    }

    /**
     * Edit an existing {@link Stalag}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"{id}/edit"})
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("stalag", genericDao.get(Stalag.class, id));
        return "stalag/edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"edit"})
    public String getEditFormRedirect(@RequestParam("id") long id, Model model) {
        return "redirect:/stalag/" + id + "/edit";
    }
    
    /**
     * Submit a {@link Stalag}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(@Valid @ModelAttribute("stalag") Stalag stalag, BindingResult result, Model model, HttpSession session, Locale locale) {
        
        if (result.hasErrors()) {
            return "stalag/edit";
        }

        /* Database validation. */
        if (stalag.getId() == null && genericDao.containsEntry(Stalag.class, "name", stalag.getName())) {
            result.rejectValue("name", "errors.entry_already_exists");
            return "stalag/edit";
        }

        genericDao.save(stalag);

        String message = messageSource.getMessage("info.successfullySaved",
                new Object[] { stalag.getName() }, locale);
        session.setAttribute("standardInfo", message);

//        return "redirect:/stalag/" + stalag.getId() + "/edit";
        return "redirect:/stalag/create";
    }

    /**
     * Delete a {@link Stalag}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "delete")
    public String deleteObject(@RequestParam("id") long id, Model model, HttpSession session, Locale locale) {

        Stalag stalag = getObject(id);
        String name = stalag.getName();

        // Check if the element is in use to avoid a constraint violation.
        if (!genericDao.containsEntry(Person.class, "stalag", stalag)) {
            genericDao.delete(stalag);
            String message = messageSource.getMessage("info.deleted", new Object[] { name }, locale);
            session.setAttribute("standardInfo", message);

        } else {
            String message = messageSource.getMessage("errors.elementInUseCannotDelete", new Object[] { name }, locale);
            session.setAttribute("standardError", message);
        }

        return "redirect:/stalag/create";
    }
    
    /**
     * Get an {@link Stalag}, e.g. in JSON.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public @ResponseBody Stalag getObject(@PathVariable long id) {
        return genericDao.get(Stalag.class, id);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Stalag.class, new EntityPropertyEditor(Stalag.class, genericDao));
    }
}
