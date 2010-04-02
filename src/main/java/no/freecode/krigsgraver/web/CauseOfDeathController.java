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
import no.freecode.krigsgraver.model.NamedEntity;
import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.apache.log4j.Logger;
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
@RequestMapping(value = "/causeOfDeath")
public class CauseOfDeathController {

    private static final Logger logger = Logger.getLogger(CauseOfDeathController.class);
    
    @Autowired
    private GenericDao genericDao;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("causeOfDeaths")
    public List<CauseOfDeath> getCauseOfDeaths() {
        return getList();
    }
    
    /**
     * List all the causeOfDeaths.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_PARTNER"})
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public List<CauseOfDeath> getList() {
        return genericDao.getAll(CauseOfDeath.class, Order.asc("name"));
    }

//    /**
//     * Show a {@link Mmmm}.
//     */
//    @RequestMapping(method = RequestMethod.GET, value = {"{id}/view"})
//    public String getView(@PathVariable long id, Model model) {
//        model.addAttribute("mmmm", genericDao.get(Mmmm.class, id));
//        return "mmmm/view";
//    }
    
    /**
     * Create a new {@link CauseOfDeath}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute("causeOfDeath", new CauseOfDeath());
        return "causeOfDeath/edit";
    }

    /**
     * Edit an existing {@link CauseOfDeath}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"{id}/edit"})
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("causeOfDeath", genericDao.get(CauseOfDeath.class, id));
        return "causeOfDeath/edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"edit"})
    public String getEditFormRedirect(@RequestParam("id") long id, Model model) {
        return "redirect:/causeOfDeath/" + id + "/edit";
    }
    
    /**
     * Submit a {@link CauseOfDeath}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(@Valid @ModelAttribute("causeOfDeath") CauseOfDeath causeOfDeath, BindingResult result, Model model, HttpSession session, Locale locale) {
        
        if (result.hasErrors()) {
            return "causeOfDeath/edit";
        }

        /* Database validation. */
        if (causeOfDeath.getId() == null && genericDao.containsEntry(CauseOfDeath.class, "name", causeOfDeath.getName())) {
            result.rejectValue("name", "errors.entry_already_exists");
            return "causeOfDeath/edit";
        }

        genericDao.save(causeOfDeath);

        String message = messageSource.getMessage("info.successfullySaved",
                new Object[] { causeOfDeath.getName() }, locale);
        session.setAttribute("standardInfo", message);

//        return "redirect:/causeOfDeath/" + causeOfDeath.getId() + "/edit";
        return "redirect:/causeOfDeath/create";
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "merge")
    public String merge(@RequestParam("fromId") long fromId, @RequestParam("toId") long toId, 
                Model model, HttpSession session, Locale locale) {

        if (fromId != toId) {
            NamedEntity from = genericDao.get(CauseOfDeath.class, fromId);
            String fromName = from.getName();
            NamedEntity to = genericDao.get(CauseOfDeath.class, toId);
            String toName = to.getName();

            int affectedEntities = genericDao.replaceOccurrencesOfSQL("person_causeofdeath", "causesofdeath_id", fromId, toId);

            // Should be no references left, so delete the entity.
            genericDao.delete(from);

            logger.info("CauseOfDeath - Merged " + affectedEntities + " occurrences of " + from.getName() + " (" + from.getId() + ") with " +
                    to.getName() + " (" + to.getId() + ")");
    
            String message = messageSource.getMessage("info.successfullyMerged",
                    new Object[] { affectedEntities, fromName, toName }, locale);
            session.setAttribute("standardInfo", message);

        } else {
            logger.warn("Tried to merge entity with self");
        }

        
        return "redirect:/causeOfDeath/create";
    }
    
    /**
     * Delete a {@link CauseOfDeath}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "delete")
    public String deleteObject(@RequestParam("id") long id, Model model, HttpSession session, Locale locale) {

        CauseOfDeath causeOfDeath = getObject(id);
        String name = causeOfDeath.getName();

        // Check if the element is in use to avoid a constraint violation.
        if (!genericDao.containsEntry(Person.class, "causesOfDeath", causeOfDeath)) {
            genericDao.delete(causeOfDeath);
            String message = messageSource.getMessage("info.deleted", new Object[] { name }, locale);
            session.setAttribute("standardInfo", message);

        } else {
            String message = messageSource.getMessage("errors.elementInUseCannotDelete", new Object[] { name }, locale);
            session.setAttribute("standardError", message);
        }

        return "redirect:/causeOfDeath/create";
    }
    
    /**
     * Get an {@link CauseOfDeath}, e.g. in JSON.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_PARTNER"})
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public @ResponseBody CauseOfDeath getObject(@PathVariable long id) {
        return genericDao.get(CauseOfDeath.class, id);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(CauseOfDeath.class, new EntityPropertyEditor(CauseOfDeath.class, genericDao));
    }
}
