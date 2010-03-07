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

import no.freecode.krigsgraver.model.Nationality;
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
 * Edit and display {@link Nationality} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/nationality")
public class NationalityController {

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("nationalities")
    public List<Nationality> getNationalities() {
        return getList();
    }
    
    /**
     * List all the nationalities.
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public @ResponseBody List<Nationality> getList() {
        return genericDao.getAll(Nationality.class, Order.asc("name"));
    }

    /**
     * Create a new {@link Nationality}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute("nationality", new Nationality());
        return "nationality/edit";
    }

    /**
     * Edit an existing {@link Nationality}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"{id}/edit"})
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("nationality", genericDao.get(Nationality.class, id));
        return "nationality/edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"edit"})
    public String getEditFormRedirect(@RequestParam("id") long id, Model model) {
        return "redirect:/nationality/" + id + "/edit";
    }
    
    /**
     * Submit a {@link Nationality}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(@Valid @ModelAttribute("nationality") Nationality nationality, BindingResult result, Model model, HttpSession session, Locale locale) {
        
        if (result.hasErrors()) {
            return "nationality/edit";
        }

        /* Database validation. */
/* TODO: enable later
        if (genericDao.containsEntry(Nationality.class, "name", nationality.getName())) {
            result.rejectValue("name", "errors.entry_already_exists");
            return "nationality/edit";
        }
*/

        genericDao.save(nationality);

        String message = messageSource.getMessage("info.successfullySaved",
                new Object[] { nationality.getName() }, locale);
        session.setAttribute("standardInfo", message);

//        return "redirect:/nationality/" + nationality.getId() + "/edit";
        return "redirect:/nationality/create";
    }

    /**
     * Delete a {@link Nationality}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "delete")
    public String deleteObject(@RequestParam("id") long id, Model model, HttpSession session, Locale locale) {

        Nationality nationality = getObject(id);
        String name = nationality.getName();

        // Check if the element is in use to avoid a constraint violation.
        if (!genericDao.containsEntry(Person.class, "nationality", nationality)) {
            genericDao.delete(nationality);
            String message = messageSource.getMessage("info.deleted", new Object[] { name }, locale);
            session.setAttribute("standardInfo", message);

        } else {
            String message = messageSource.getMessage("errors.elementInUseCannotDelete", new Object[] { name }, locale);
            session.setAttribute("standardError", message);
        }

        return "redirect:/nationality/create";
    }
    
    /**
     * Get an {@link Nationality}, e.g. in JSON.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public @ResponseBody Nationality getObject(@PathVariable long id) {
        return genericDao.get(Nationality.class, id);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Nationality.class, new EntityPropertyEditor(Nationality.class, genericDao));
    }
}
