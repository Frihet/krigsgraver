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

import no.freecode.krigsgraver.model.Camp;
import no.freecode.krigsgraver.model.Person;
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
 * Edit and display {@link Camp} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/camp")
public class CampController {

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("camps")
    public List<Camp> getCamps() {
        return getList();
    }
    
    /**
     * List all the camps.
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public List<Camp> getList() {
        return genericDao.getAll(Camp.class, Order.asc("name"));
    }

    /**
     * Create a new {@link Camp}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute("camp", new Camp());
        return "camp/edit";
    }

    /**
     * Edit an existing {@link Camp}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"{id}/edit"})
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("camp", genericDao.get(Camp.class, id));
        return "camp/edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"edit"})
    public String getEditFormRedirect(@RequestParam("id") long id, Model model) {
        return "redirect:/camp/" + id + "/edit";
    }
    
    /**
     * Submit a {@link Camp}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(@Valid @ModelAttribute("camp") Camp camp, BindingResult result, Model model, HttpSession session, Locale locale) {
        
        if (result.hasErrors()) {
            return "camp/edit";
        }

        /* Database validation. */
        if (camp.getId() == null && genericDao.containsEntry(Camp.class, "name", camp.getName())) {
            result.rejectValue("name", "errors.entry_already_exists");
            return "camp/edit";
        }

        genericDao.save(camp);

        String message = messageSource.getMessage("info.successfullySaved",
                new Object[] { camp.getName() }, locale);
        session.setAttribute("standardInfo", message);

//        return "redirect:/camp/" + camp.getId() + "/edit";
        return "redirect:/camp/create";
    }

    /**
     * Delete a {@link Camp}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "delete")
    public String deleteObject(@RequestParam("id") long id, Model model, HttpSession session, Locale locale) {

        Camp camp = getObject(id);
        String name = camp.getName();

        // Check if the element is in use to avoid a constraint violation.
        if (!genericDao.containsEntry(Person.class, "camp", camp)) {
            genericDao.delete(camp);
            String message = messageSource.getMessage("info.deleted", new Object[] { name }, locale);
            session.setAttribute("standardInfo", message);

        } else {
            String message = messageSource.getMessage("errors.elementInUseCannotDelete", new Object[] { name }, locale);
            session.setAttribute("standardError", message);
        }

        return "redirect:/camp/create";
    }
    
    /**
     * Get an {@link Camp}, e.g. in JSON.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public @ResponseBody Camp getObject(@PathVariable long id) {
        return genericDao.get(Camp.class, id);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Camp.class, new EntityPropertyEditor(Camp.class, genericDao));
    }
}
