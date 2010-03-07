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
import no.freecode.krigsgraver.model.Rank;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping(value = "/rank")
public class RankController {

    private static final Logger logger = Logger.getLogger(RankController.class);
    
    @Autowired
    private GenericDao genericDao;
    
    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("ranks")
    public List<Rank> getRanks() {
        return genericDao.getAll(Rank.class, Order.asc("name"));
    }

    /**
     * List all the {@link Rank}s.
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public @ResponseBody List<Rank> getList() {
        return genericDao.getAll(Rank.class, Order.asc("name"));
    }

    /**
     * Create a new {@link Rank}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
//        model.addAttribute("ranks", genericDao.getAll(Rank.class, Order.asc("name")));
        model.addAttribute("rank", new Rank());
        return "rank/edit";
    }

    /**
     * Edit an existing {@link Rank}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "{id}/edit")
    public String getEditForm(@PathVariable long id, Model model) {
//        model.addAttribute("ranks", genericDao.getAll(Rank.class, Order.asc("name")));
        model.addAttribute("rank", genericDao.get(Rank.class, id));
        return "rank/edit";
    }

    /**
     * Submit a {@link Rank}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(@Valid @ModelAttribute("rank") Rank rank, BindingResult result, Model model, HttpSession session, Locale locale) {

        logger.debug(rank.getName());
        
        if (result.hasErrors()) {
            logger.debug("validation errors: ");
            for (ObjectError error : result.getAllErrors()) {
                logger.debug(error);
            }
            return "rank/edit";
        }

        genericDao.save(rank);

        String message = messageSource.getMessage("info.successfullySaved",
                new Object[] { rank.getName() }, locale);
        session.setAttribute("standardInfo", message);

        return "redirect:/rank/" + rank.getId() + "/edit";
    }

    /**
     * Delete a {@link Rank}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.DELETE, value = "*/edit")
    public String deleteObject(@ModelAttribute("rank") Rank rank, Model model, HttpSession session, Locale locale) {
        
//        genericDao.delete(getObject(id));
        genericDao.delete(rank);
        
        String message = messageSource.getMessage("info.deletedEntry", null, locale);
        session.setAttribute("standardInfo", message);
        return "redirect:/rank/create";
    }

    /**
     * Get an {@link Rank}, e.g. in JSON.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public @ResponseBody Rank getObject(@PathVariable long id) {
        return genericDao.get(Rank.class, id);
    }
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Rank.class, new EntityPropertyEditor(Rank.class, genericDao));
    }
}
